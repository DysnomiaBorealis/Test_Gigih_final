package com.example.gigih_final2.data.network
import android.os.Parcelable
import com.example.gigih_final2.data.response.ReportData
import com.example.gigih_final2.data.response.ReportsData
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ReportApiResponse(

    @field:SerializedName("result")
    val result: Result? = null,

    @field:SerializedName("statusCode")
    val statusCode: Int? = null
)

    data class Result(
        @field:SerializedName("features")
        val features: List<Disaster>? = null,

        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("objects")
        val objects: Objects? = null,
    )

data class Output(

    @field:SerializedName("geometries")
    val geometries: List<GeometriesItem?>? = null,

    @field:SerializedName("type")
    val type: String? = null
)

data class GeometriesItem(

    @field:SerializedName("coordinates")
    val coordinates: List<Double?>? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("properties")
    val properties: Properties? = null
)

data class Objects(

    @field:SerializedName("output")
    val output: Output? = null
)

    data class Disaster(
        @field:SerializedName("geometry")
        val geometry: Geometry? = null,

        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("properties")
        val properties: Properties? = null
    )


    data class Properties(

        @field:SerializedName("image_url")
        val imageUrl: String? = null,

        @field:SerializedName("disaster_type")
        val disasterType: String? = null,

        @field:SerializedName("created_at")
        val createdAt: String? = null,

        @field:SerializedName("source")
        val source: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("url")
        val url: String? = null,

        @field:SerializedName("tags")
        val tags: Tags? = null,

        @field:SerializedName("partner_icon")
        val partnerIcon: String? = null,

        @field:SerializedName("report_data")
        @JsonAdapter(ReportsData::class)
        val reportData: ReportData? = null,

        @field:SerializedName("pkey")
        val pkey: String? = null,

        @field:SerializedName("text")
        val text: String? = null,

        @field:SerializedName("partner_code")
        val partnerCode: String? = null,

        @field:SerializedName("status")
        val status: String? = null
    )


    @Parcelize
    data class Tags(
        @field:SerializedName("instance_region_code")
        val instanceRegionCode: String? = null,

        @field:SerializedName("district_id")
        val districtId: String? = null,

        @field:SerializedName("local_area_id")
        val localAreaId: String? = null,

        @field:SerializedName("region_code")
        val regionCode: String? = null
    ) : Parcelable

    @Parcelize
    data class Geometry(
        @field:SerializedName("coordinates")
        val coordinates: List<Double>? = null,

        @field:SerializedName("type")
        val type: String? = null
    ) : Parcelable


    @Parcelize
    data class PersonLocation(
        @field:SerializedName("lng")
        val lng: Double? = null,

        @field:SerializedName("lat")
        val lat: Double? = null
    ) : Parcelable

    @Parcelize
    data class FireRadius(

        @field:SerializedName("lng")
        val lng: Double? = null,

        @field:SerializedName("lat")
        val lat: Double? = null
    ) : Parcelable


    @Parcelize
    data class FireLocation(
        @field:SerializedName("lng")
        val lng: Double? = null,

        @field:SerializedName("lat")
        val lat: Double? = null
    ) : Parcelable
