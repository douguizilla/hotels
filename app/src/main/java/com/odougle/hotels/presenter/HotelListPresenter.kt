package com.odougle.hotels.presenter

import com.odougle.hotels.model.Hotel
import com.odougle.hotels.model.HotelRepository
import com.odougle.hotels.view.HotelListView

class HotelListPresenter(
    private val view: HotelListView,
    private val repository: HotelRepository
) {
    fun searchHotels(term: String){
        repository.search(term){ hotels ->
            view.showHotels(hotels)
        }
    }

    fun showHotelDetails(hotel: Hotel){
        view.showHotelDetails(hotel)
    }
}