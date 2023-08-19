package com.example.gigih_final2

import com.example.gigih_final2.utils.DateHelper
import com.example.gigih_final2.utils.ProvinceHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import java.lang.IllegalArgumentException

class HelperTest {

    @Test
    fun `getAvailableProvince returns correct list of provinces`() {
        val expected = listOf("Aceh", "Bali", "Banten" ) // Add all expected provinces here
        val result = ProvinceHelper.getAvailableProvince()
        assertEquals(expected, result)
    }

    @Test
    fun `getProvinceCode returns correct province code`() {
        val provinceName = "Aceh"
        val expectedCode = "AC" // Assuming "AC" is the code for Aceh
        val result = ProvinceHelper.getProvinceCode(provinceName)
        assertEquals(expectedCode, result)
    }

    @Test
    fun `getProvinceCode throws exception for unsupported province`() {
        val unsupportedProvince = "Unsupported"
        assertThrows(IllegalArgumentException::class.java) {
            ProvinceHelper.getProvinceCode(unsupportedProvince)
        }
    }

    @Test
    fun `beautifyDate returns correct formatted date`() {
        val rawDate = "2023-07-31T12:00:00.000Z"
        val expected = "31 Jul 2023, 12:00"
        val result = DateHelper.beautifyDate(rawDate)
        assertEquals(expected, result)
    }
}
