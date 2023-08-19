package com.example.gigih_final2.presentation
import android.app.Application
import com.example.gigih_final2.domain.UseCase.ThemeDarkUseCase
import com.example.gigih_final2.utils.ThemeHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var darkThemeUseCase: ThemeDarkUseCase
    override fun onCreate() {
        super.onCreate()
        if (darkThemeUseCase.isDarkThemeEnabled()) {
            ThemeHelper.enableDarkTheme()
        } else {
            ThemeHelper.disableDarkTheme()
        }
    }

}
