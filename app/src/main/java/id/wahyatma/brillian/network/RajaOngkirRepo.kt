package id.wahyatma.brillian.network

import id.wahyatma.brillian.localDB.roomDb.CekOngkirDb
import id.wahyatma.brillian.localDB.roomDb.CekResiEntity
import id.wahyatma.brillian.localDB.sharedPref.*

class RajaOngkirRepo (
        private val api: EndPoint,
        private val pref: CekOngkirSharedPref,
        private val db: CekOngkirDb
        ){

    suspend fun fetchKota() = api.getCity()

    suspend fun fetchDistrik( idKota: String ) = api.getSubdistrict(idKota)

    suspend fun fetchCost( origin: String,
                          originType: String,
                          destination: String,
                          destinationType: String,
                          weight: String,
                          courier: String ) = api.getCost(origin, originType, destination, destinationType, weight, courier)

    suspend fun fetchTrack(
            waybill: String,
            courier: String ) = api.getWayBill( waybill, courier )

    fun savePref(type: String, id: String, name: String){
        when(type){
            origin -> {
                pref.setData(prefOriginId, id)
                pref.setData(prefOriginName, name)
            }
            destination -> {
                pref.setData(prefDestinationId, id)
                pref.setData(prefDestinationName, name)
            }
        }
    }

    fun getDataPref(): List<PrefModel>{
        return listOf(
                PrefModel(origin, pref.getString(prefOriginId), pref.getString(prefOriginName)),
                PrefModel(destination, pref.getString(prefDestinationId), pref.getString(prefDestinationName))
        )
    }

    suspend fun saveTracking(cekResiEntity: CekResiEntity ){
        db.cekResiDao().insert( cekResiEntity )
    }

    suspend fun deleteTracking(cekResiEntity: CekResiEntity ){
        db.cekResiDao().delete( cekResiEntity )
    }

    fun getTracking() = db.cekResiDao().select()
}