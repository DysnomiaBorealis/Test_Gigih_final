package com.example.gigih_final2.usecase

import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.domain.UseCase.GetDisasterReportsUseCaseImpl
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

class GetDisasterReportsUseCaseImplTest {

    @Mock
    private lateinit var disasterRepository: DisasterRepository

    private lateinit var useCase: GetDisasterReportsUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetDisasterReportsUseCaseImpl(disasterRepository)
    }

    @Test
    fun `test get disaster reports`() = runBlocking {
        // Given
        val mockData = listOf<Entities>()
        val mockResult = ResultState.Success(mockData)
        Mockito.`when`(disasterRepository.getDisasterReports(null, null)).thenReturn(flowOf(mockResult))

        // When
        val result = useCase.getAllReportData(null, null)

        // Then
        assertEquals(mockResult, result)
    }
}
