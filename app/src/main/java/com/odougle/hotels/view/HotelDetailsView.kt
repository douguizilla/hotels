package com.odougle.hotels.view

import com.odougle.hotels.model.Hotel

interface HotelDetailsView {
    fun showHotelDetails(hotel: Hotel)
    fun errorHotelNotFound()
}