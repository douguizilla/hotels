package com.odougle.hotels.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.repository.HotelRepository

class HotelDetailsViewModel(
    private val repository: HotelRepository
) : ViewModel(){

    fun loadHotelDetails(id: Long): LiveData<Hotel>{
        return repository.hotelById(id)
    }
}