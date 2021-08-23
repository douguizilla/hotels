package com.odougle.hotels.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.odougle.hotels.R
import com.odougle.hotels.databinding.ActivityHotelBinding
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.view.fragments.AboutDialogFragment
import com.odougle.hotels.view.fragments.HotelDetailsFragment
import com.odougle.hotels.view.fragments.HotelFormFragment
import com.odougle.hotels.view.fragments.HotelListFragment

class HotelActivity : AppCompatActivity(), HotelListFragment.OnHotelClickListener,
    HotelListFragment.OnHotelDeletedListener,
    SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener,
    HotelFormFragment.OnHotelSavedListener {

    private val binding: ActivityHotelBinding by lazy {
        ActivityHotelBinding.inflate(layoutInflater)
    }

    private val listFragment: HotelListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as HotelListFragment
    }

    private var lastSearchTerm: String = ""
    private var searchView: SearchView? = null

    private var hotelIdSelected: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener{
            listFragment.hideDeleteMode()
            HotelFormFragment.newInstance().open(supportFragmentManager)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString(EXTRA_SEARCH_TERM, lastSearchTerm)
        outState?.putLong(EXTRA_HOTEL_ID_SELECTED, hotelIdSelected)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastSearchTerm = savedInstanceState?.getString(EXTRA_SEARCH_TERM) ?: ""
        hotelIdSelected = savedInstanceState?.getLong(EXTRA_HOTEL_ID_SELECTED) ?: 0
    }


    //carregar as ações do meu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.hotel, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.setOnActionExpandListener(this)

        searchView = searchItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.hint_search)
        searchView?.setOnQueryTextListener(this)

        if (lastSearchTerm.isNotEmpty()) {
            Handler().post {
                val query = lastSearchTerm
                searchItem.expandActionView()
                searchView?.setQuery(query, true)
                searchView?.clearFocus()
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_info ->
                AboutDialogFragment().show(supportFragmentManager, "sobre")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onHotelClick(hotel: Hotel) {
        if (isTablet()) {
            hotelIdSelected = hotel.id
            showDetailsFragment(hotel.id)
        } else {
            showDetailsActivity(hotel.id)
        }
    }

    private fun showDetailsActivity(hotelId: Long) {
        HotelDetailsActivity.open(this, hotelId)
    }

    private fun isTablet() = resources.getBoolean(R.bool.tablet)
    private fun isSmartphone() = resources.getBoolean(R.bool.smartphone)

    private fun showDetailsFragment(hotelId: Long) {
        val fragment = HotelDetailsFragment.newInstance(hotelId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, HotelDetailsFragment.TAG_DETAILS)
            .commit()
    }

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(newText: String?): Boolean {
        lastSearchTerm = newText ?: ""
        listFragment.search(lastSearchTerm)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?) = true

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        lastSearchTerm = ""
        listFragment.clearSearch()
        return true
    }

    companion object {
        const val EXTRA_SEARCH_TERM = "lastSearch"
        const val EXTRA_HOTEL_ID_SELECTED = "lastSelectedId"
    }

    override fun onHotelSaved(hotel: Hotel) {
        listFragment.search(lastSearchTerm)
    }

    override fun onHotelsDeleted(hotels: List<Hotel>) {
        if (hotels.find { it.id == hotelIdSelected } != null) {
            val fragment =
                supportFragmentManager.findFragmentByTag(HotelDetailsFragment.TAG_DETAILS)
            if (fragment != null) {
                supportFragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit()
            }
        }
    }

}