package com.example.gigih_final2.domain.UseCase

import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.domain.repository.DisasterRepository
import com.example.gigih_final2.utils.ProvinceHelper
import com.example.gigih_final2.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDisasterReportsUseCaseImpl @Inject constructor(
    private val repository: DisasterRepository
    ) : GetDisasterReportsUseCase {

    override fun getAllReportData(
        provinceName: String?,
        disasterType: String?
    ): Flow<ResultState<List<Entities>>> {
        return repository.getDisasterReports(provinceName, disasterType)
    }

    override fun getAvailableProvince(): List<String> = ProvinceHelper.getAvailableProvince()
}
