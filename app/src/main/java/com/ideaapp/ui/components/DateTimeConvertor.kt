package com.ideaapp.ui.components

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

object DateTimeConvertor {
    fun convertLongToDateTime(time: Long): String {
        val format = SimpleDateFormat("E, MMM dd, HH:mm", Locale.getDefault())
        return format.format(time)
    }

    fun convertLongToDate(date: Long): String {
        val format = SimpleDateFormat("E, MMM dd, yyyy", Locale.getDefault())
        return format.format(date)
    }

    fun convertIntToTime(hours: Int, minutes: Int): String {
        return String.format("%02d:%02d", hours, minutes)
    }

    fun isValidDateTime(combinedDateTime: Long): Boolean {
        val now = LocalDateTime.now()
        val selectedDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(combinedDateTime),
            ZoneId.systemDefault()
        )
        // Проверка, находится ли выбранная дата в прошлом
        return !selectedDateTime.isBefore(now)
    }
}