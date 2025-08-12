package com.andreas.keuanganku.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtils {
    @SuppressLint("ConstantLocale")
    private val dateTimeFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    @SuppressLint("ConstantLocale")
    private val dateOnlyFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun parseToTimestamp(dateTimeString: String): Long? {
        return try {
            val date = dateTimeFormatter.parse(dateTimeString)
            date?.time
        } catch (e: Exception) {
            null
        }
    }

    fun formatTimestamp(timestamp: Long?): String {
        return if (timestamp == null) {
            ""
        } else {
            dateTimeFormatter.format(Date(timestamp))
        }
    }

    fun parseDateOnlyToTimestamp(dateString: String): Long? {
        return try {
            val date = dateOnlyFormatter.parse(dateString)
            date?.time
        } catch (_: Exception) {
            null
        }
    }

    fun formatTimestampToDateOnly(timestamp: Long?): String {
        return if (timestamp == null) {
            ""
        } else {
            dateOnlyFormatter.format(Date(timestamp))
        }
    }

    fun formatISODateToLongStringDate(dateString: String): String {
        return try {
            val inputFormatter = dateOnlyFormatter // "yyyy-MM-dd"
            val date = inputFormatter.parse(dateString)
            val outputFormatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH)
            if (date != null) outputFormatter.format(date) else ""
        } catch (e: Exception) {
            ""
        }
    }

    fun formatTimestampToLongStringDate(timestamp: Long?): String {
        return if (timestamp == null) {
            ""
        } else {
            try {
                val date = Date(timestamp)
                val outputFormatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH)
                outputFormatter.format(date)
            } catch (e: Exception) {
                ""
            }
        }
    }


}
