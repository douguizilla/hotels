package com.odougle.hotels.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odougle.hotels.databinding.ActivityHotelBinding

class HotelActivity : AppCompatActivity() {

    private val binding: ActivityHotelBinding by lazy {
        ActivityHotelBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}