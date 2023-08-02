package com.example.gigih_final2.domain.repository

import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface DisasterRepository {
 fun getDisasterReports(provinceName: String?, disasterType: String?): Flow<ResultState<List<Entities>>>
}