package com.odougle.hotels.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.odougle.hotels.databinding.FragmentHotelDetailsBinding
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.model.MemoryRepository
import com.odougle.hotels.presenter.HotelDetailsPresenter

class HotelDetailsFragment : Fragment(), HotelDetailsView {

    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

    private val presenter = HotelDetailsPresenter(this, MemoryRepository)
    private var hotel: Hotel? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadHotelDetails(arguments?.getLong(EXTRA_HOTEL_ID, -1) ?: -1)
    }

    override fun showHotelDetails(hotel: Hotel) {
        this.hotel = hotel
        binding.txtName.text = hotel.name
        binding.txtAddress.text = hotel.address
        binding.rtbRating.rating = hotel.rating
    }

    override fun errorHotelNotFound() {
        TODO("Not yet implemented")
    }


    companion object {
        const val TAG_DETAILS = "tagDetalhe"
        private const val EXTRA_HOTEL_ID = "hotelId"

        fun newInstance(id: Long) = HotelDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_HOTEL_ID, id)
            }
        }
    }

}