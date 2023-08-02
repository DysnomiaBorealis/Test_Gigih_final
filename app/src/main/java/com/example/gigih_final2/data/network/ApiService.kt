package com.example.gigih_final2.data.network
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reports")
    suspend fun getDisasterReport(
        @Query("admin") provinceCode: String? = null,
        @Query("disaster") disasterType: String? = null,
        @Query("timeperiod") time: Int = 604800
    ): Response<ReportApiResponse>
}
