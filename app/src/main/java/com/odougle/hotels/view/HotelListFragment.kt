package com.odougle.hotels.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.ListFragment
import com.odougle.hotels.R
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.model.MemoryRepository
import com.odougle.hotels.presenter.HotelListPresenter

class HotelListFragment : ListFragment(), HotelListView, ActionMode.Callback,
    AdapterView.OnItemLongClickListener {

    private val presenter = HotelListPresenter(this, MemoryRepository)
    private var actionMode: ActionMode? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
        listView.onItemLongClickListener = this
    }

    override fun showHotels(hotels: List<Hotel>) {
        val adapter = ArrayAdapter<Hotel>(requireContext(), android.R.layout.simple_list_item_1, hotels)
        listAdapter = adapter
    }

    override fun showHotelDetails(hotel: Hotel) {
       if(activity is OnHotelClickListener){
           val listener = activity as OnHotelClickListener
           listener.onHotelClick(hotel)
       }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val hotel = l?.getItemAtPosition(position) as Hotel
        presenter.selectHotel(hotel)
    }

    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {
        val consumed = (actionMode == null)
        if(consumed){
            val hotel = parent?.getItemAtPosition(position) as Hotel
            presenter.showDeleteMode()
            presenter.selectHotel(hotel)
        }
        return consumed
    }

    interface OnHotelClickListener{
        fun onHotelClick(hotel: Hotel)
    }

    fun search(text: String){
        presenter.searchHotels(text)
    }

    fun clearSearch(){
        presenter.searchHotels("")
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        TODO("Not yet implemented")
    }

    override fun showDeleteMode() {
        TODO("Not yet implemented")
    }

    override fun hideDeleteMode() {
        TODO("Not yet implemented")
    }

    override fun showSelectedHotels(hotels: List<Hotel>) {
        TODO("Not yet implemented")
    }

    override fun updateSelectionCountText(count: Int) {
        TODO("Not yet implemented")
    }


}