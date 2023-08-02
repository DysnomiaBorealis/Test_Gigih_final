package com.example.gigih_final2.utils

import android.util.Log

object Logger {
    private val TAG = "DEBUG LOG"
    fun w(msg: String) {
        Log.w(TAG, "w: $msg")
    }

    fun e(msg: String) {
        Log.e(TAG, "e: $msg")
    }
}