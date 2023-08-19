package com.example.gigih_final2.domain.UseCase

import com.example.gigih_final2.data.local.AvailablePeriod

interface ReportPeriodUseCase {
    fun setReportPeriod(period: AvailablePeriod)
    fun getReportPeriod(): AvailablePeriod
    fun displayTimePeriod(): String
    fun adjustTimePeriod(period: String)
    fun displayAvailableTimeIntervals(): Array<String>
}
