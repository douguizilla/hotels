package com.odougle.hotels.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.odougle.hotels.R
import com.odougle.hotels.databinding.ActivityHotelBinding
import com.odougle.hotels.model.Hotel

class HotelActivity : AppCompatActivity(), HotelListFragment.OnHotelClickListener {

    private val binding: ActivityHotelBinding by lazy {
        ActivityHotelBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onHotelClick(hotel: Hotel) {
        if(isTablet()){
            showDetailsFragment(hotel.id)
        }else{
            showDetailsActivity(hotel.id)
        }
    }

    private fun showDetailsActivity(hotelId: Long) {
        HotelDetailsActivity.open(this,hotelId)
    }

    private fun isTablet() = findViewById<View>(R.id.details) != null

    private fun showDetailsFragment(hotelId: Long){
        val fragment = HotelDetailsFragment.newInstance(hotelId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, HotelDetailsFragment.TAG_DETAILS)
            .commit()
    }

}