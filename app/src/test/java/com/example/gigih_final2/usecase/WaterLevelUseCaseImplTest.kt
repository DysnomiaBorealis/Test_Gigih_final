package com.example.gigih_final2.usecase

import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.domain.UseCase.WaterLevelUseCaseImpl
import com.example.gigih_final2.domain.repository.DisasterRepository
import com.example.gigih_final2.utils.ResultState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WaterLevelUseCaseImplTest {

    @Mock
    private lateinit var disasterRepository: DisasterRepository

    private lateinit var useCase: WaterLevelUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = WaterLevelUseCaseImpl(disasterRepository)
    }

    @Test
    fun `test check water level threshold`() = runBlocking {
        // Given
        val mockData = listOf<Entities>()
        val mockResult = ResultState.Success(mockData)
        Mockito.`when`(disasterRepository.getDisasterReports(null, "Flood")).thenReturn(flowOf(mockResult))

        // When
        val result = useCase.checkWaterLevelThreshold()

        // Then

        assertEquals(ResultState.Success(true), result)
    }
}
