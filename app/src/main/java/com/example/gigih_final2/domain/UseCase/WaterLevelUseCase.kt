package com.example.gigih_final2.domain.UseCase

import com.example.gigih_final2.utils.ResultState

interface WaterLevelUseCase {
    suspend fun checkWaterLevelThreshold(): ResultState<Boolean>
}
