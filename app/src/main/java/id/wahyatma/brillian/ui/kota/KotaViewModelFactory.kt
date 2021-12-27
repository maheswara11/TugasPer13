package id.wahyatma.brillian.ui.kota

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.wahyatma.brillian.network.RajaOngkirRepo

class KotaViewModelFactory (
        private val repo: RajaOngkirRepo
        ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KotaViewModel(repo) as T
    }
}