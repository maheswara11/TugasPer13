package id.wahyatma.brillian.ui.kota

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import id.wahyatma.brillian.network.RajaOngkirRepo
import id.wahyatma.brillian.network.Resource
import id.wahyatma.brillian.network.response.DistrikResponse
import id.wahyatma.brillian.network.response.KotaResponse

class KotaViewModel(
        private val repository: RajaOngkirRepo
): ViewModel() {

    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val kotaResponse: MutableLiveData<Resource<KotaResponse>> = MutableLiveData()
    val distrikResponse: MutableLiveData<Resource<DistrikResponse>> = MutableLiveData()

    init {
        fetchKota()
    }

    fun fetchKota() = viewModelScope.launch{
        kotaResponse.value = Resource.Loading()
        try {
            val response = repository.fetchKota()
            kotaResponse.value = Resource.Success(response.body()!!)
        }catch (e: Exception){
            kotaResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun fetchDistrik(id: String) = viewModelScope.launch{
        val response = repository.fetchDistrik(id)
        distrikResponse.value = Resource.Loading()
        try {
            distrikResponse.value = Resource.Success(response.body()!!)
        }catch (e: Exception){
            distrikResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun savePref(type: String, id: String, name: String){
        repository.savePref(type, id, name)
    }
}