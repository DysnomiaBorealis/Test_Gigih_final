package com.example.gigih_final2.domain.UseCase

import com.example.gigih_final2.domain.UseCase.ReportPeriodUseCase
import com.example.gigih_final2.data.local.AvailablePeriod
import com.example.gigih_final2.data.local.UserSettingsManager
import javax.inject.Inject

class ReportPeriodUseCaseImpl @Inject constructor(private val userSettingsManager: UserSettingsManager) : ReportPeriodUseCase {

    override fun setReportPeriod(period: AvailablePeriod) {
        userSettingsManager.setReportPeriod(period)
    }

    override fun getReportPeriod(): AvailablePeriod {
        return userSettingsManager.getReportPeriod()
    }

    override fun displayTimePeriod(): String {
        return when (getReportPeriod()) {
            AvailablePeriod.ONE_WEEK -> "Last Week"
            AvailablePeriod.THREE_DAYS -> "Last Three Days"
            AvailablePeriod.TWO_DAYS -> "Last Two Days"
            AvailablePeriod.ONE_DAY -> "Today"
        }
    }

    override fun adjustTimePeriod(period: String) {
        val availablePeriod = when (period) {
            "Last Week" -> AvailablePeriod.ONE_WEEK
            "Last Three Days" -> AvailablePeriod.THREE_DAYS
            "Last Two Days" -> AvailablePeriod.TWO_DAYS
            "Today" -> AvailablePeriod.ONE_DAY
            else -> throw IllegalArgumentException("No period listed")
        }
        setReportPeriod(availablePeriod)
    }

    override fun displayAvailableTimeIntervals(): Array<String> {
        return AvailablePeriod.values().map { period ->
            when (period) {
                AvailablePeriod.ONE_WEEK -> "Last Week"
                AvailablePeriod.THREE_DAYS -> "Last Three Days"
                AvailablePeriod.TWO_DAYS -> "Last Two Days"
                AvailablePeriod.ONE_DAY -> "Today"
            }
        }.toTypedArray()
    }
}
