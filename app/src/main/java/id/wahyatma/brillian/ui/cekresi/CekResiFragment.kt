package id.wahyatma.brillian.ui.cekresi

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.wahyatma.brillian.databinding.FragmentCekResiBinding
import id.wahyatma.brillian.localDB.roomDb.CekResiEntity
import id.wahyatma.brillian.localDB.sharedPref.CekOngkirSharedPref
import id.wahyatma.brillian.ui.cekresi.adapter.CekResiAdapter
import id.wahyatma.brillian.ui.tracking.TrackingActivity
import id.wahyatma.brillian.ui.tracking.TrackingViewModel

class CekResiFragment : Fragment() {
    private lateinit var binding: FragmentCekResiBinding
    private val preferences by lazy { CekOngkirSharedPref( requireActivity() ) }
    private val viewModelTracking by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }
    lateinit var cekResiAdapter: CekResiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentCekResiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupRecyclerview()
        setupObserver()
     }

    private fun setupRecyclerview() {
        cekResiAdapter = CekResiAdapter(arrayListOf(), object : CekResiAdapter.OnAdapterListener{
            override fun OnClick(result: CekResiEntity) {
                startActivity(Intent(requireContext(), TrackingActivity::class.java)
                        .putExtra("is_tracking", true)
                        .putExtra("dataDesi", result.resi)
                        .putExtra("dataCourier", result.courier)
                )
            }

            override fun OnDelete(result: CekResiEntity) {
                AlertDialog.Builder(requireActivity())
                        .apply {
                            setTitle("HAPUS RESI")
                            setMessage("hapus No. Resi ${result.resi} ?")
                            setPositiveButton("Hapus"){_, _ ->
                                viewModel.deleteResi( result )
                                Toast.makeText(requireContext(), "Data berhasil dihapus!", Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("Batal"){_, _-> }
                        }
                        .show()
            }
        })
        binding.listWaybill.adapter = cekResiAdapter
    }

    private fun setupObserver() {
        viewModelTracking.cekResi.observe( viewLifecycleOwner, Observer {
            Log.d("_cekresientity", it.toString() )
            cekResiAdapter.setData( it )
        })
    }

    private fun setupListener() {
        binding.editWaybill.setOnClickListener {
            startActivity(Intent( requireActivity(), TrackingActivity::class.java) )
        }
    }

}
