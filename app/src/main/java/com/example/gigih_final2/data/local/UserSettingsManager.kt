package com.example.gigih_final2.data.local

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import com.example.gigih_final2.data.local.AvailablePeriod


@Singleton
class UserSettingsManager @Inject constructor(@ApplicationContext context: Context) {

    private val preference = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)

    fun isDarkThemeEnabled(): Boolean {
        return preference.getBoolean(KEY_IS_DARK_THEME, DEFAULT_DARK_THEME)
    }

    fun getReportPeriod(): AvailablePeriod {
        val periodName = preference.getString(KEY_REPORT_PERIOD, DEFAULT_REPORT_PERIOD.name)
        return AvailablePeriod.valueOf(periodName!!)
    }

    fun setDarkThemeEnabled(isEnabled: Boolean) {
        preference.edit {
            putBoolean(KEY_IS_DARK_THEME, isEnabled)
        }
    }

    fun setReportPeriod(period: AvailablePeriod) {
        preference.edit {
            putString(KEY_REPORT_PERIOD, period.name)
        }
    }

    companion object {
        private const val PREFERENCE_FILE_NAME = "UserSettings"
        private const val KEY_IS_DARK_THEME = "DarkThemeEnabled"
        private const val KEY_REPORT_PERIOD = "ReportPeriod"
        private val DEFAULT_DARK_THEME = false
        private val DEFAULT_REPORT_PERIOD = AvailablePeriod.ONE_DAY
    }
}