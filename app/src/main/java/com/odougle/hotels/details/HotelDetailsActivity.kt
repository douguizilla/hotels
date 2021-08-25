package com.odougle.hotels.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odougle.hotels.R
import com.odougle.hotels.databinding.ActivityHotelDetailsBinding

class HotelDetailsActivity : AppCompatActivity() {

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
        fun open(context: Context, hotelId: Long){
            context.startActivity(Intent(context, HotelDetailsActivity::class.java).apply {
                putExtra(EXTRA_HOTEL_ID, hotelId)
            })
        }
    }
}