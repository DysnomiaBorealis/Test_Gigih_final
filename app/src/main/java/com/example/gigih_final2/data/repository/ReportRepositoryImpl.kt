package com.example.gigih_final2.data.repository

import com.example.gigih_final2.data.network.ApiService
import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.domain.repository.DisasterRepository
import com.example.gigih_final2.utils.Logger
import com.example.gigih_final2.utils.ProvinceHelper
import com.example.gigih_final2.utils.ResultState
import com.example.gigih_final2.utils.convertReportApiResponseToDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DisasterRepository {

    override fun getDisasterReports(
        provinceName: String?,
        disasterType: String?
    ): Flow<ResultState<List<Entities>>> = flow {
        try {
            emit(ResultState.Loading<List<Entities>>())
            kotlinx.coroutines.delay(500L)
            var code: String? = null
            val disaster = disasterType

            if (provinceName != null) {
                code = ProvinceHelper.getProvinceCode(provinceName)
            }

            val apiResponse = apiService.getDisasterReport(
                provinceCode = code,
                disasterType = disaster
            )
            Logger.e(apiResponse.toString())
            if (apiResponse.isSuccessful) {
                val body =
                    apiResponse.body() ?: throw IllegalArgumentException("This should not be happening.")
                val reportList =
                    convertReportApiResponseToDomain(body).filter { it.imgUrl != null }
                if (reportList.isEmpty()) {
                    throw IllegalArgumentException("reports unavailable")
                }
                emit(ResultState.Success(reportList))
            } else {
                emit(ResultState.Error(Exception("Bad Request. Api does not handle properly")))
            }

        } catch (e: IllegalArgumentException) {
            emit(ResultState.Error(Exception(e.message.toString())))
        } catch (e: NoSuchElementException) {
            emit(ResultState.Error(Exception(e.message.toString())))
        } catch (e: Exception) {
            emit(ResultState.Error(Exception("Unexpected Error. ${e.message}")))
            Logger.e("NonsenseException : ${e.message}")
        }
    }.flowOn(Dispatchers.IO)
}
