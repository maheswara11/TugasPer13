package id.wahyatma.brillian.network

import retrofit2.Response
import retrofit2.http.*
import id.wahyatma.brillian.network.response.BiayaResponse
import id.wahyatma.brillian.network.response.DistrikResponse
import id.wahyatma.brillian.network.response.KotaResponse
import id.wahyatma.brillian.network.response.TrackingResponse

interface EndPoint {
    @GET("city")
    suspend fun getCity(): Response<KotaResponse>

    @GET("subdistrict")
    suspend fun getSubdistrict(
            @Query( "city") city: String
    ): Response<DistrikResponse>

    @FormUrlEncoded
    @POST("cost")
    suspend fun getCost(
            @Field("origin" ) origin: String,
            @Field("originType" ) originType: String,
            @Field("destination" ) destination: String,
            @Field("destinationType" ) destinationType: String,
            @Field("weight" ) weight: String,
            @Field("courier" ) courier: String
    ): Response<BiayaResponse>

    @FormUrlEncoded
    @POST("waybill" )
    suspend fun getWayBill(
            @Field("waybill") waybill: String,
            @Field("courier") courier: String
    ): Response<TrackingResponse>
}