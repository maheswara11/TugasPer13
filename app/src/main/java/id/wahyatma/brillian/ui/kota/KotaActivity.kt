package id.wahyatma.brillian.ui.kota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import id.wahyatma.brillian.R

class KotaActivity : AppCompatActivity(), KodeinAware{

    override val kodein by closestKodein()
    private val viewModelFactory: KotaViewModelFactory by instance()
    private lateinit var viewModel: KotaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kota)
        setupViewModel()
        setupObserver()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(KotaViewModel::class.java)
    }

    private fun setupObserver(){
        viewModel.titleBar.observe(this, Observer {
            supportActionBar?.title = it
        })
    }
}