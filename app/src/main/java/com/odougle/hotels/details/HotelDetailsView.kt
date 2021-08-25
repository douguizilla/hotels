package com.odougle.hotels.details

import com.odougle.hotels.model.Hotel

interface HotelDetailsView {
    fun showHotelDetails(hotel: Hotel)
    fun errorHotelNotFound()
}