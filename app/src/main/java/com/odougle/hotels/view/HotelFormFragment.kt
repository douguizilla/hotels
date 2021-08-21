package com.odougle.hotels.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.odougle.hotels.R
import com.odougle.hotels.databinding.FragmentHotelFormBinding
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.model.MemoryRepository
import com.odougle.hotels.presenter.HotelFormPresenter

class HotelFormFragment : DialogFragment(), HotelFormView {


    private var _binding: FragmentHotelFormBinding? = null
    private val binding get() = _binding!!

    private val presenter = HotelFormPresenter(this, MemoryRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hotelId = arguments?.getLong(EXTRA_HOTEL_ID, 0) ?: 0
        presenter.loadHotel(hotelId)
        binding.edtAddress.setOnEditorActionListener { _, i, _ ->
            handleKeyboardEvent(i)
        }
        dialog?.setTitle(R.string.action_new_hotel)
        //abre o teclado virtual ao exibir o dialog
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
    }

    private fun handleKeyboardEvent(actionId: Int) : Boolean {
        if(EditorInfo.IME_ACTION_DONE == actionId){
            val hotel = saveHotel()
            if(hotel != null){
                if(activity is OnHotelSavedListener){
                    val listener = activity as OnHotelSavedListener
                    listener.onHotelSaved(hotel)
                }
                // fechar o dialog
                dialog?.dismiss()
                return true
            }
        }
        return false
    }

    private fun saveHotel(): Hotel? {
        val hotel = Hotel()
        val hotelId = arguments?.getLong(EXTRA_HOTEL_ID, 0) ?: 0
        hotel.id = hotelId
        hotel.name = binding.edtName.text.toString()
        hotel.address = binding.edtAddress.text.toString()
        hotel.rating = binding.rtbRating.rating
        if(presenter.saveHotel(hotel)){
            return hotel
        }
            return null
    }

    override fun showHotel(hotel: Hotel) {
        binding.edtName.setText(hotel.name)
        binding.edtAddress.setText(hotel.address)
        binding.rtbRating.rating = hotel.rating
    }

    override fun errorInvalidHotel() {
        Toast.makeText(requireContext(), R.string.error_invalid_hotel, Toast.LENGTH_SHORT).show()
    }

    override fun errorSaveHotel() {
        Toast.makeText(requireContext(), R.string.error_hotel_not_found, Toast.LENGTH_SHORT).show()
    }

    fun open(fm: FragmentManager){
        if(fm.findFragmentByTag(DIALOG_TAG) == null){
            show(fm,DIALOG_TAG)
        }
    }

    interface OnHotelSavedListener{
        fun onHotelSaved(hotel: Hotel)
    }

    companion object{
        private const val EXTRA_HOTEL_ID = "hotel_id"
        private const val DIALOG_TAG = "editDialog"

        fun newInstance(hotelId: Long = 0) = HotelFormFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_HOTEL_ID, hotelId)
            }
        }
    }
}