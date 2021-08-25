package com.odougle.hotels.repository.memory

import com.odougle.hotels.model.Hotel
import com.odougle.hotels.repository.HotelRepository

object MemoryRepository : HotelRepository {

    private var nextId = 1L
    private val hotelList = mutableListOf<Hotel>()

    init {
        save(Hotel(0, "New Beach Hotel", "Av. Boa Viagem", 4.5f))
        save(Hotel(0, "Recife Hotel", "Av. Boa Viagem", 4.0f))
        save(Hotel(0, "Canario Hotel", "Av. dos Navegadores", 3.0f))
        save(Hotel(0, "Bianca Beach Hotel", "Av. da Paixao", 4.5f))
        save(Hotel(0, "Caribe Hotel", "Av. Carolina Lima", 4.0f))
        save(Hotel(0, "Olavio Hotel", "Av. dos Caminhoneiros", 3.0f))
        save(Hotel(0, "New Orleans Hotel", "Av. Pereira dos Santos", 4.8f))
        save(Hotel(0, "Cristina Hotel", "Av. da Criança", 4.1f))
        save(Hotel(0, "Lima Hotel", "Av. dos Pedreiros", 3.7f))
    }

    override fun save(hotel: Hotel) {
        if (hotel.id == 0L) {
            hotel.id = nextId++
            hotelList.add(hotel)
        } else {
            val index = hotelList.indexOfFirst {
                it.id == hotel.id
            }
            if (index > -1)
                hotelList[index] = hotel
            else
                hotelList.add(hotel)
        }
    }

    override fun remove(vararg hotels: Hotel) {
        hotelList.removeAll(hotels)
    }

    override fun hotelById(id: Long, callback: (Hotel?) -> Unit) {
        callback(hotelList.find { hotel ->
            hotel.id == id
        })
    }

    override fun search(term: String, callback: (List<Hotel>) -> Unit) {
        val result = if(term.isEmpty()) hotelList
        else hotelList.filter{
            it.name.toUpperCase().contains(term.toUpperCase())
        }
        callback( result.sortedBy { it.name }
        )
    }
}