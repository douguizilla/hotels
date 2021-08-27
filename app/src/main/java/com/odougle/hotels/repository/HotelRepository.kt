package com.odougle.hotels.repository

import androidx.lifecycle.LiveData
import com.odougle.hotels.model.Hotel

interface HotelRepository {
    fun save(hotel: Hotel)
    fun remove(vararg hotels: Hotel)
    fun hotelById(id: Long): LiveData<Hotel>
    fun search(term: String): LiveData<List<Hotel>>
}