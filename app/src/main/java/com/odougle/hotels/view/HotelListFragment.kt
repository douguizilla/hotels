package com.odougle.hotels.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.model.MemoryRepository
import com.odougle.hotels.presenter.HotelListPresenter

class HotelListFragment : ListFragment(), HotelListView {

    private val presenter = HotelListPresenter(this, MemoryRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
    }

    override fun showHotels(hotels: List<Hotel>) {
        val adapter = ArrayAdapter<Hotel>(requireContext(), android.R.layout.simple_list_item_1, hotels)
        listAdapter = adapter
    }

    override fun showHotelDetails(hotel: Hotel) {
        //implementarei em breve
    }
}