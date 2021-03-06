package com.odougle.hotels.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.odougle.hotels.R
import com.odougle.hotels.databinding.ActivityHotelDetailsBinding
import com.odougle.hotels.form.HotelFormFragment
import com.odougle.hotels.model.Hotel

class HotelDetailsActivity : AppCompatActivity(){

    private val binding: ActivityHotelDetailsBinding  by lazy{
        ActivityHotelDetailsBinding.inflate(layoutInflater)
    }

    private val hotelId: Long by lazy { intent.getLongExtra(EXTRA_HOTEL_ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(savedInstanceState == null){
            showHotelDetaisFragment()
        }
    }

    private fun showHotelDetaisFragment() {
        val fragment = HotelDetailsFragment.newInstance(hotelId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, HotelDetailsFragment.TAG_DETAILS)
            .commit()
    }

    companion object{
        private const val EXTRA_HOTEL_ID = "hotel_id"
        fun open(activity: Activity, hotelId: Long){
            activity.startActivityForResult(
                Intent(activity, HotelDetailsActivity::class.java).apply {
                    putExtra(EXTRA_HOTEL_ID, hotelId)
                }
            ,0)
        }
    }
}