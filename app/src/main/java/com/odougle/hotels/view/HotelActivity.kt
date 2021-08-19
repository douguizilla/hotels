package com.odougle.hotels.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.odougle.hotels.R
import com.odougle.hotels.databinding.ActivityHotelBinding
import com.odougle.hotels.model.Hotel

class HotelActivity : AppCompatActivity(), HotelListFragment.OnHotelClickListener,
    SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private val binding: ActivityHotelBinding by lazy {
        ActivityHotelBinding.inflate(layoutInflater)
    }

    private var lastSearchTerm: String = ""
    private var searchView: SearchView? = null

    //carregar as ações do meu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.hotel, menu)
        return true
    }

    //tratar as açoes do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onHotelClick(hotel: Hotel) {
        if (isTablet()) {
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

}