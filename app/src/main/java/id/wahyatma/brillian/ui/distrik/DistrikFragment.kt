package id.wahyatma.brillian.ui.distrik

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.wahyatma.brillian.R
import id.wahyatma.brillian.databinding.FragmentDistrikBinding
import id.wahyatma.brillian.network.Resource
import id.wahyatma.brillian.network.response.DistrikResponse
import id.wahyatma.brillian.ui.distrik.adapter.DistrikAdapter
import id.wahyatma.brillian.ui.kota.KotaViewModel


class DistrikFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(KotaViewModel::class.java) }
    private lateinit var binding: FragmentDistrikBinding
    private val type by lazy { requireActivity().intent.getStringExtra("type") }
    private val id_kota by lazy { requireArguments().getString("city_id") }
    private val name_kota by lazy { requireArguments().getString("city_name") }
    private lateinit var distrikadapter: DistrikAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDistrikBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecyclerview()
        setupListener()
        setupObserver()
    }

    private fun setupRecyclerview() {
        distrikadapter = DistrikAdapter( arrayListOf(), object : DistrikAdapter.OnDistrikAdapterListener{
            override fun onClick(result: DistrikResponse.DistrikRajaOngkir.DataResult) {
                viewModel.savePref(
                        type!!,
                        result.subdistrict_id,
                        result.subdistrict_name+", "+result.city)
                requireActivity().finish()
            }
        })
        binding.listSubdistrict.adapter = distrikadapter
    }

    private fun setupListener() {
        binding.refreshSubdistrict.setOnRefreshListener {
            viewModel.fetchDistrik( id_kota!! )
        }
    }

    private fun setupView() {
        viewModel.titleBar.postValue(getString(R.string.select_districk))
    }

    private fun setupObserver(){
        viewModel.distrikResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> binding.refreshSubdistrict.isRefreshing = true
                is Resource.Success ->{
                    binding.refreshSubdistrict.isRefreshing = false
                    Log.d("districtRajaOngkir", it.data!!.rajaongkir.toString())
                    distrikadapter.setData( it.data.rajaongkir.results )
                }
                is Resource.Error ->{
                    binding.refreshSubdistrict.isRefreshing = false
                    Log.d("districtRajaOngkir", "EROORRNYA "+it.message.toString())
                }
            }
        })
    }
}