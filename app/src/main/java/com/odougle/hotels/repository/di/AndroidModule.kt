package com.odougle.hotels.repository.di

import com.odougle.hotels.details.HotelDetailsViewModel
import com.odougle.hotels.form.HotelFormViewModel
import com.odougle.hotels.list.HotelListViewModel
import com.odougle.hotels.repository.HotelRepository
import com.odougle.hotels.repository.room.HotelDatabase
import com.odougle.hotels.repository.room.RoomRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val androidModule = module {
    single { this }
    single {
        RoomRepository(HotelDatabase.getDatabase(context = get())) as HotelRepository
    }

    viewModel {
        HotelListViewModel(repository = get())
    }

    viewModel {
        HotelDetailsViewModel(repository = get())
    }

    viewModel {
        HotelFormViewModel(repository = get())
    }


}