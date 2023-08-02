package com.example.gigih_final2.utils

import com.example.gigih_final2.data.network.ReportApiResponse
import com.example.gigih_final2.data.response.*
import com.example.gigih_final2.domain.Entity.*

fun convertReportApiResponseToDomain(reportsApiResponse: ReportApiResponse): MutableList<Entities> {
    val reportList = mutableListOf<Entities>()
    reportsApiResponse.result?.objects?.output?.geometries?.let { item ->
        item.map {
            val coordinates = Coordinates(
                lat = (it?.coordinates?.get(1) ?: 0.0), lng = it?.coordinates?.get(0) ?: 0.0
            )
            val title = it?.properties?.title ?: "No title"
            var body = it?.properties?.text ?: "No Description"
            val imgUrl = it?.properties?.imageUrl
            val date = it?.properties?.createdAt ?: ""
            val beautifyDate = DateHelper.beautifyDate(date)
            val disasterType = it?.properties?.disasterType ?: "Not Specified"

            if (body.isEmpty()) {
                body = "No Description"
            }

            it?.properties?.reportData?.let { reportData ->
                when (reportData) {
                    is EarthquakeReportData -> {
                        val reports = EarthquakeEntity(
                            body,
                            coordinates,
                            imgUrl,
                            title,
                            disasterType,
                            beautifyDate,
                            reportData.reportType
                        )
                        reportList.add(reports)
                    }

                    is FloodReportData -> {
                        val reports = FloodEntity(
                            body,
                            coordinates,
                            imgUrl,
                            title,
                            disasterType,
                            beautifyDate,
                            reportData.reportType,
                            reportData.floodDepth ?: 0
                        )
                        reportList.add(reports)
                    }

                    is FireReportData -> {
                        val reports = FireEntity(
                            body,
                            coordinates,
                            imgUrl,
                            title,
                            disasterType,
                            beautifyDate,
                            reportData.reportType,
                            reportData.fireDistance ?: 0.0
                        )
                        reportList.add(reports)
                    }

                    is VolcanoReportData -> {
                        val reports = VolcanoEntity(
                            body,
                            coordinates,
                            imgUrl,
                            title,
                            disasterType,
                            beautifyDate,
                            reportData.reportType
                        )
                        reportList.add(reports)
                    }

                    else -> {}
                }
            } ?: run {
                val reports = GeneralEntities(
                    body,
                    coordinates,
                    imgUrl,
                    title,
                    disasterType,
                    beautifyDate,
                )
                reportList.add(reports)
            }

        }
    }
    return reportList
}