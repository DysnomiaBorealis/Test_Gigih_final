package com.example.gigih_final2.injection

import com.example.gigih_final2.domain.UseCase.GetDisasterReportsUseCase
import com.example.gigih_final2.domain.UseCase.GetDisasterReportsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun provideGetDisasterReportsUseCase(impl: GetDisasterReportsUseCaseImpl): GetDisasterReportsUseCase

}
