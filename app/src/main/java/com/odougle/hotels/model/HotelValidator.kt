package com.odougle.hotels.model

class HotelValidator {
    fun validate(info: Hotel) = with(info){
        checkName(name) && checkAddress(address)
    }

    private fun checkAddress(address: String) = address.length in 4..80

    private fun checkName(name: String) = name.length in 2..20
}