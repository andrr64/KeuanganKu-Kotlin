package com.andreas.keuangankuplus.util

import java.text.NumberFormat
import java.util.Locale



object NumberFormat {
    fun formatToCurrency(value: Long, locale: Locale = Locale("in", "ID")): String {
        val formatter = NumberFormat.getNumberInstance(locale)
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        return formatter.format(value)
    }

    fun formatToCurrency(value: Double, locale: Locale = Locale("in", "ID")): String {
        val formatter = NumberFormat.getNumberInstance(locale)
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        return formatter.format(value)
    }
}

