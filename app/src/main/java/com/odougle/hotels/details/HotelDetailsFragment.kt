package com.odougle.hotels.details

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.odougle.hotels.R
import com.odougle.hotels.databinding.FragmentHotelDetailsBinding
import com.odougle.hotels.form.HotelFormFragment
import com.odougle.hotels.model.Hotel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HotelDetailsFragment : Fragment(){

    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HotelDetailsViewModel by viewModel()
    private var hotel: Hotel? = null

    private var shareActionProvider : ShareActionProvider? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.hotel_details, menu)
        val shareItem = menu?.findItem(R.id.action_share)
        shareActionProvider = MenuItemCompat.getActionProvider(shareItem) as? ShareActionProvider
        setShareIntent()
    }

    private fun setShareIntent() {
        val text = getString(R.string.share_text, hotel?.name, hotel?.rating)
        shareActionProvider?.setShareIntent(Intent(Intent.ACTION_SEND).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getLong(EXTRA_HOTEL_ID, -1) ?: -1
        viewModel.loadHotelDetails(id).observe(viewLifecycleOwner, Observer { hotel ->
            if (hotel != null){
                showHotelDetails(hotel)
            }else{
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this)
                    ?.commit()
                errorHotelNotFound()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.action_edit){
            HotelFormFragment
                .newInstance(hotel?.id ?: 0)
                .open(requireFragmentManager())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showHotelDetails(hotel: Hotel) {
        this.hotel = hotel
        binding.txtNameDetails.text = hotel.name
        binding.txtAddress.text = hotel.address
        binding.rtbRatingDetails.rating = hotel.rating
    }

    private fun errorHotelNotFound() {
        binding.txtNameDetails.text = getString(R.string.error_hotel_not_found)
        binding.txtAddress.visibility = View.GONE
        binding.rtbRatingDetails.visibility = View.GONE
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