package com.example.gigih_final2.domain.UseCase

import com.example.gigih_final2.domain.UseCase.ThemeDarkUseCase
import com.example.gigih_final2.data.local.UserSettingsManager
import com.example.gigih_final2.utils.ThemeHelper
import javax.inject.Inject

class ThemeDarkUseCaseImpl @Inject constructor(private val userSettingsManager: UserSettingsManager) : ThemeDarkUseCase {

    override fun enableDarkTheme() {
        userSettingsManager.setDarkThemeEnabled(true)
        ThemeHelper.enableDarkTheme()
    }

    override fun disableDarkTheme() {
        userSettingsManager.setDarkThemeEnabled(false)
        ThemeHelper.disableDarkTheme()
    }

    override fun isDarkThemeEnabled(): Boolean {
        return userSettingsManager.isDarkThemeEnabled()
    }

    override fun DarkThemeEnabled() {
        val isDarkThemeEnabled = isDarkThemeEnabled()
        if (isDarkThemeEnabled) {
            disableDarkTheme()
        } else {
            enableDarkTheme()
        }
    }
}
