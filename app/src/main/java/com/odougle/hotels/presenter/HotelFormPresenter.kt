package com.odougle.hotels.presenter

import com.odougle.hotels.model.Hotel
import com.odougle.hotels.model.HotelRepository
import com.odougle.hotels.model.HotelValidator
import com.odougle.hotels.view.HotelFormView
import java.lang.Exception
import kotlin.Exception

class HotelFormPresenter(
    private val view: HotelFormView,
    private val repository: HotelRepository
) {
    private val validator = HotelValidator()

    fun loadHotel(id: Long){
        repository.hotelById(id){hotel ->
            if(hotel != null){
                view.showHotel(hotel)
            }
        }
    }

    fun saveHotel(hotel: Hotel): Boolean{
        return if(validator.validate(hotel)){
            try {
                repository.save(hotel)
                true
            }catch (e: Exception){
                view.errorSaveHotel()
                false
            }
        }else{
            view.errorInvalidHotel()
            false
        }
    }

}