package com.odougle.hotels.repository.di

import com.odougle.hotels.details.HotelDetailsPresenter
import com.odougle.hotels.details.HotelDetailsView
import com.odougle.hotels.form.HotelFormPresenter
import com.odougle.hotels.form.HotelFormView
import com.odougle.hotels.list.HotelListPresenter
import com.odougle.hotels.list.HotelListView
import com.odougle.hotels.repository.HotelRepository
import com.odougle.hotels.repository.sqlite.SQLiteRepository
import org.koin.dsl.module.module

val androidModule = module {
    single { this }
    single {
        SQLiteRepository(context = get()) as HotelRepository
    }

    factory { (view: HotelListView) ->
        HotelListPresenter(
            view,
            repository = get()
        )
    }

    factory { (view: HotelDetailsView) ->
        HotelDetailsPresenter(
            view,
            repository = get()
        )
    }

    factory { (view: HotelFormView) ->
        HotelFormPresenter(
            view,
            repository = get()
        )
    }
}