package com.example.gigih_final2.domain.UseCase
import com.example.gigih_final2.domain.Entity.FloodEntity
import com.example.gigih_final2.domain.UseCase.WaterLevelUseCase
import com.example.gigih_final2.domain.repository.DisasterRepository
import com.example.gigih_final2.utils.ResultState
import javax.inject.Inject

class WaterLevelUseCaseImpl @Inject constructor(
    private val disasterRepository: DisasterRepository
) : WaterLevelUseCase {


    private val WATER_LEVEL_THRESHOLD = 50

    override suspend fun checkWaterLevelThreshold(): ResultState<Boolean> {
        return try {
            val result = disasterRepository.getDisasterReports(null, "Flood") // Assuming "Flood" is the type for flood reports

            // Extract the flood reports from the result
            val floodReports = when (result) {
                is ResultState.Success<*> -> {
                    val dataList = result.data as? List<*>
                    dataList?.filterIsInstance<FloodEntity>() ?: emptyList()
                }
                else -> emptyList()
            }

            val latestFloodReport = floodReports.lastOrNull() // Assuming you want the latest report
            if (latestFloodReport?.floodDepth ?: 0 > WATER_LEVEL_THRESHOLD) {
                ResultState.Success(true)
            } else {
                ResultState.Success(false)
            }
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }
}


