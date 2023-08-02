package com.example.gigih_final2.domain.Entity

abstract class Entities {
    abstract val coordinates: Coordinates
    abstract val title: String
    abstract val body: String
    abstract val date: String
    abstract val imgUrl: String?
    abstract val disasterType: String
}

data class Coordinates (
    val lat: Double,
    val lng: Double
        )

