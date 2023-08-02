package com.example.gigih_final2.data.response
import com.example.gigih_final2.utils.Logger
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.lang.IllegalArgumentException
import java.lang.reflect.Type

class ReportsData : JsonDeserializer<ReportData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ):   ReportData{
        json?.let {
            Logger.e("Breakpoint 1.")
            val jsonObject = it.asJsonObject
            Logger.e("Breakpoint 2 : $jsonObject.")
            val type = jsonObject["report_type"].asString
            Logger.e("Breakpoint 3 : $type.")


            return when (type) {
                "flood" -> {
                    val floodDepth = jsonObject["flood_depth"].asInt
                    FloodReportData(type, floodDepth)
                }
                "haze" -> {
                    HazeReportData(type)
                }
                "earthquake" -> {
                    EarthquakeReportData(type)
                }
                "fire" -> {
                    val fireDistance = jsonObject["fireDistance"].asDouble
                    FireReportData(type, fireDistance)
                }
                "wind" -> {
                    WindReportData(type)
                }
                "volcano" -> {
                    VolcanoReportData(type)
                }
                else -> {
                    return EarthquakeReportData("earthquake")
                }
            }
        }
        throw IllegalArgumentException("Invalid JSON")
    }
}


sealed class ReportData

data class FloodReportData(
    @field:SerializedName("report_type")
    val reportType: String,
    @field:SerializedName("flood_depth")
    val floodDepth: Int?
) : ReportData()

data class FireReportData(
    @field:SerializedName("report_type")
    val reportType: String,
    @field:SerializedName("fireDistance")
    val fireDistance: Double?
) : ReportData()

data class EarthquakeReportData(
    @field:SerializedName("report_type")
    val reportType: String,
) : ReportData()

data class WindReportData(
    @field:SerializedName("report_type")
    val reportType: String,
) : ReportData()

data class HazeReportData(
    @field:SerializedName("report_type")
    val reportType: String,
) : ReportData()

data class VolcanoReportData(
    @field:SerializedName("report_type")
    val reportType: String,
) : ReportData()