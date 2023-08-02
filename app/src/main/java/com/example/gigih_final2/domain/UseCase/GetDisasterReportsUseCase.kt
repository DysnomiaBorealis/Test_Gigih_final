package com.example.gigih_final2.domain.UseCase

import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface GetDisasterReportsUseCase {
    fun getAllReportData(
        provinceName: String? = null,
        disasterType: String? = null,
    ): Flow<ResultState<List<Entities>>>

    fun getAvailableProvince(): List<String>

}
