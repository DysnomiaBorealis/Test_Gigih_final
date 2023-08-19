package com.example.gigih_final2.injection

import com.example.gigih_final2.domain.UseCase.ThemeDarkUseCase
import com.example.gigih_final2.domain.UseCase.ReportPeriodUseCase
import com.example.gigih_final2.domain.UseCase.ReportPeriodUseCaseImpl
import com.example.gigih_final2.domain.UseCase.ThemeDarkUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppUseCaseModule {

    @Binds
    abstract fun bindThemeDarkUseCase(impl: ThemeDarkUseCaseImpl): ThemeDarkUseCase

    @Binds
    abstract fun bindReportPeriodUseCase(impl: ReportPeriodUseCaseImpl): ReportPeriodUseCase
}
