package com.odougle.hotels.presenter

import com.odougle.hotels.model.HotelRepository
import com.odougle.hotels.view.HotelDetailsView

class HotelDetailsPresenter (
    private val view: HotelDetailsView,
    private val repository: HotelRepository
) {
    fun loadHotelDetails(id: Long){
        repository.hotelById(id){hotel ->
            if(hotel != null){
                view.showHotelDetails(hotel)
            }else{
                view.errorHotelNotFound()
            }
        }
    }
}