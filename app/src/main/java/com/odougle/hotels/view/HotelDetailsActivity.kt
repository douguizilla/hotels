package com.odougle.hotels.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odougle.hotels.databinding.ActivityHotelDetailsBinding

class HotelDetailsActivity : AppCompatActivity() {

    private val binding: ActivityHotelDetailsBinding by lazy{
        ActivityHotelDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}