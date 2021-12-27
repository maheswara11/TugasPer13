package id.wahyatma.brillian.ui.biayaOngkir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.wahyatma.brillian.network.RajaOngkirRepo

class BiayaViewModelFactory (
        private val repo: RajaOngkirRepo
        ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BiayaViewModel(repo) as T
    }
}