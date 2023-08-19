package com.example.gigih_final2.repository

import com.example.gigih_final2.data.network.ApiService
import com.example.gigih_final2.data.network.ReportApiResponse
import com.example.gigih_final2.data.network.Result
import com.example.gigih_final2.data.repository.ReportRepositoryImpl
import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.utils.ResultState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ReportRepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var repository: ReportRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = ReportRepositoryImpl(apiService)
    }

    @Test
    fun `test get disaster reports`() = runBlocking {
        // Given
        val mockData = listOf<Entities>()
        val mockApiResponse = ReportApiResponse(result = Result(features = emptyList()))

        val mockResponse: Response<ReportApiResponse> = Response.success(mockApiResponse)
        Mockito.`when`(apiService.getDisasterReport(null, null)).thenReturn(mockResponse)


        val expectedState = ResultState.Success(mockData)

        // When
        val result = repository.getDisasterReports(null, null).first()

        // Then
        assertEquals(expectedState, result)
    }

}
