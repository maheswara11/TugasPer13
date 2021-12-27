package id.wahyatma.brillian.ui.tracking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.wahyatma.brillian.R
import id.wahyatma.brillian.databinding.FragmentTrackingBinding

class TrackingFragment : Fragment() {

    private lateinit var binding: FragmentTrackingBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }
    private val isTracking by lazy { requireActivity().intent.getBooleanExtra("is_tracking", false) }
    private val dataResi by lazy { requireActivity().intent.getStringExtra("dataDesi") }
    private val dataCourier by lazy { requireActivity().intent.getStringExtra("dataCourier") }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTrackingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        if (isTracking){
            viewModel.fetchTracking(
                    dataResi!!,
                    dataCourier!!)
            findNavController().navigate( R.id.action_trackingFragment_to_trackingResultFragment )
        }
    }

    private fun setupListener() {
        binding.buttonTrack.setOnClickListener {
            viewModel.fetchTracking( binding.editWaybill.text.toString(), binding.listCourier.selectedItem.toString())
            findNavController().navigate( R.id.action_trackingFragment_to_trackingResultFragment )
        }
    }
}