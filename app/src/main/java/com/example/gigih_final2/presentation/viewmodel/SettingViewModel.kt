package com.example.gigih_final2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.work.*
import com.example.gigih_final2.domain.UseCase.ThemeDarkUseCase
import com.example.gigih_final2.domain.UseCase.ReportPeriodUseCase
import com.example.gigih_final2.domain.Worker.WaterLevelWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val themeDarkUseCase: ThemeDarkUseCase,
    private val reportPeriodUseCase: ReportPeriodUseCase,
    private val workManager: WorkManager
) : ViewModel() {

    val userTimePreference: String
        get() = reportPeriodUseCase.displayTimePeriod()

    fun activateDarkTheme() = themeDarkUseCase.enableDarkTheme()
    fun deactivateDarkTheme() = themeDarkUseCase.disableDarkTheme()
    val isDarkModeActive: Boolean
        get() = themeDarkUseCase.isDarkThemeEnabled()

    fun displayAvailableTimeIntervals() = reportPeriodUseCase.displayAvailableTimeIntervals()
    fun adjustTimePeriod(period: String) {
        reportPeriodUseCase.adjustTimePeriod(period)
    }

    fun startWaterLevelWorker() {
        val workRequest = PeriodicWorkRequestBuilder<WaterLevelWorker>(15, TimeUnit.MINUTES)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "WaterLevelWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
