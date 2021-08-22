package com.odougle.hotels.presenter

import com.odougle.hotels.model.Hotel
import com.odougle.hotels.model.HotelRepository
import com.odougle.hotels.view.HotelListView

class HotelListPresenter(
    private val view: HotelListView,
    private val repository: HotelRepository
) {
    private var lastTerm = ""
    private var inDeleteMode = false
    private val selectedItems = mutableListOf<Hotel>()

    fun searchHotels(term: String) {
        lastTerm = term
        repository.search(term) { hotels ->
            view.showHotels(hotels)
        }
    }

    fun selectHotel(hotel: Hotel){
        if (inDeleteMode) {
            toogleHotelSelected(hotel)
            if (selectedItems.size == 0) {
                view.hideDeleteMode()
            } else {
                view.updateSelectionCountText(selectedItems.size)
                view.showSelectedHotels(selectedItems)
            }

        } else {
            view.showHotelDetails(hotel)
        }
    }

    fun showHotelDetails(hotel: Hotel) {
        view.showHotelDetails(hotel)
    }

    private fun toogleHotelSelected(hotel: Hotel) {
        val existing = selectedItems.find { it.id == hotel.id }
        if(existing == null){
            selectedItems.add(hotel)
        }else{
            selectedItems.removeAll { it.id == hotel.id }
        }
    }

    fun showDeleteMode(){
        inDeleteMode = true
        view.showDeleteMode()
    }

    fun hideDeleteMode(){
        inDeleteMode = false
        selectedItems.clear()
        view.hideDeleteMode()
    }

    fun refresh(){
        searchHotels(lastTerm)
    }

    fun deleteSelected(callback: (List<Hotel>)->Unit){
        repository.remove(*selectedItems.toTypedArray())
        refresh()
        callback(selectedItems)
        hideDeleteMode()
    }

    fun init() {
        if(inDeleteMode){
            showDeleteMode()
            view.updateSelectionCountText(selectedItems.size)
            view.showSelectedHotels(selectedItems)
        }else{
            refresh()
        }
    }

}