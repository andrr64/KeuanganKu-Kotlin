package com.andreas.keuangankuplus.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtils {
    @SuppressLint("ConstantLocale")
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())


    fun parseToTimestamp(dateTimeString: String): Long? {
        return try {
            val date = formatter.parse(dateTimeString)
            date?.time
        } catch (e: Exception) {
            null
        }
    }

    fun formatTimestamp(timestamp: Long?): String {
        return if (timestamp == null) {
            ""
        } else {
            formatter.format(Date(timestamp))
        }
    }
}
