package id.wahyatma.brillian

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import id.wahyatma.brillian.localDB.roomDb.CekOngkirDb
import id.wahyatma.brillian.localDB.sharedPref.CekOngkirSharedPref
import id.wahyatma.brillian.network.ApiService
import id.wahyatma.brillian.network.EndPoint
import id.wahyatma.brillian.network.RajaOngkirRepo
import id.wahyatma.brillian.ui.biayaOngkir.BiayaViewModelFactory
import id.wahyatma.brillian.ui.kota.KotaViewModelFactory
import id.wahyatma.brillian.ui.tracking.TrackingViewModelFactory
import timber.log.Timber

class CekOngkirApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CekOngkirApplication))
        bind() from provider { CekOngkirSharedPref( instance() ) }
        bind() from provider { CekOngkirDb( instance() ) }
        bind<EndPoint>() with singleton { ApiService.getClient() }
        bind() from provider { RajaOngkirRepo( instance(), instance() , instance()) }

        bind() from provider { KotaViewModelFactory( instance() ) }
        bind() from provider { BiayaViewModelFactory( instance() ) }
        bind() from provider { TrackingViewModelFactory( instance() ) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant( Timber.DebugTree() )
    }

}