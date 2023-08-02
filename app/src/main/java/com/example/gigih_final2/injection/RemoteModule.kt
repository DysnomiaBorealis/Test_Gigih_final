package com.example.gigih_final2.injection

import com.example.gigih_final2.data.network.ApiService
import com.example.gigih_final2.data.response.ReportData
import com.example.gigih_final2.data.response.ReportsData
import com.example.gigih_final2.utils.Url
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .registerTypeAdapter(ReportData::class.java, ReportsData())
            .create()
        val gsonConverter = GsonConverterFactory.create(gson)
        return Retrofit
            .Builder()
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}