package com.example.gigih_final2.injection
import com.example.gigih_final2.data.repository.ReportRepositoryImpl
import com.example.gigih_final2.domain.repository.DisasterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindReportRepository(reportRepositoryImpl: ReportRepositoryImpl): DisasterRepository
}