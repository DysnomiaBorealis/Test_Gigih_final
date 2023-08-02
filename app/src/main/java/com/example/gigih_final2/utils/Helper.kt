package com.example.gigih_final2.utils
import android.icu.text.SimpleDateFormat
import com.example.gigih_final2.data.local.AvailableProvince
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.*

object ProvinceHelper {
    fun getAvailableProvince(): List<String> {
        val provinceList = AvailableProvince.values().map {
            it.name.lowercase().replace("_", " ")
        }.map { provinceName ->
            provinceName.split(" ").joinToString(" ") { it.replaceFirstChar { it.uppercase() } }
        }
        return provinceList
    }

    fun getProvinceCode(provinceName: String): String {
        try {
            return AvailableProvince.valueOf(
                provinceName.uppercase().replace(" ", "_")
            ).id
        } catch (e: Exception) {
            throw IllegalArgumentException("Sorry, the province is not yet supported", e)
        }
    }
}

object DateHelper {

    private const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val SHOW_DATE_FORMAT = "dd MMM yyyy, HH:mm"

    fun beautifyDate(rawDate: String): String {
        val apiFormat = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
        val date = apiFormat.parse(rawDate)
        val newFormat = SimpleDateFormat(SHOW_DATE_FORMAT, Locale.getDefault())

        return newFormat.format(date)
    }
}



