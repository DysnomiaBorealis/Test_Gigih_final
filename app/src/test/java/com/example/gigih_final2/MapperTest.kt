package com.example.gigih_final2

import com.example.gigih_final2.data.network.*
import com.example.gigih_final2.data.response.FloodReportData
import com.example.gigih_final2.domain.Entity.*
import com.example.gigih_final2.utils.convertReportApiResponseToDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `test convertReportApiResponseToDomain`() {
        // Mock API response data
        val mockApiResponse = ReportApiResponse(
            result = Result(
                features = listOf(
                    Disaster(
                        geometry = Geometry(
                            coordinates = listOf(1.0, 2.0)
                        ),
                        properties = Properties(
                            title = "Test Title",
                            text = "Test Description",
                            imageUrl = "http://test.com/image.jpg",
                            createdAt = "2022-01-01T10:00:00.000Z",
                            disasterType = "Flood",
                            reportData = FloodReportData(
                                reportType = "Flood Report",
                                floodDepth = 5
                            )
                        )
                    )
                )
            )
        )

        // Use the mapper function
        val convertedList = convertReportApiResponseToDomain(mockApiResponse)

        // Assertions
        assertEquals(1, convertedList.size)
        val report = convertedList[0] as FloodEntity
        assertEquals("Test Title", report.title)
        assertEquals("Test Description", report.body)
        assertEquals("http://test.com/image.jpg", report.imgUrl)
        assertEquals("Flood", report.disasterType)
        assertEquals("01 Jan 2022, 10:00", report.date)
        assertEquals(5, report.floodDepth)
    }
}
