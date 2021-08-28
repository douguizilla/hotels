package com.odougle.hotels.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.odougle.hotels.repository.HotelRepository

class HotelListViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    var hotelIdSelected: Long = -1
    private val searchItem = MutableLiveData<String>()
    private val hotels = Transformations.switchMap(searchItem){ term ->
        repository.search("%$term%")
    }

}