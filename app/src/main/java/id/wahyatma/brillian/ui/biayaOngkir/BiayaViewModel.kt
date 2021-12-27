package id.wahyatma.brillian.ui.biayaOngkir

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import id.wahyatma.brillian.localDB.sharedPref.PrefModel
import id.wahyatma.brillian.network.RajaOngkirRepo
import id.wahyatma.brillian.network.Resource
import id.wahyatma.brillian.network.response.BiayaResponse

class BiayaViewModel(
        private val repository: RajaOngkirRepo
): ViewModel() {

    val preferences: MutableLiveData<List<PrefModel>> = MutableLiveData()
    val biayaResponse: MutableLiveData<Resource<BiayaResponse>> = MutableLiveData()

    fun getPreferences(){
        preferences.value = repository.getDataPref()
        Log.d("_cekPref", "data = "+repository.getDataPref().toString())
    }

    fun fetchCost(origin: String,
                  originType: String,
                  destination: String,
                  destinationType: String,
                  weight: String,
                  courier: String
    ) = viewModelScope.launch {
        biayaResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCost(origin, originType, destination, destinationType, weight, courier)
            biayaResponse.value = Resource.Success( response.body()!! )
        }catch (e: Exception){
            biayaResponse.value = Resource.Error( e.message.toString() )
        }
    }

}
