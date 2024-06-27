package com.ideaapp.shared.compose.components

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


object DateTimeConvertor {
    fun convertLongToDateTime(time: Long): String {
        val instant = Instant.fromEpochMilliseconds(time)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return localDateTime.toString()
    }

    fun convertLongToDate(date: Long): String {
        val instant = Instant.fromEpochMilliseconds(date)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        return localDateTime.toString()
    }

    fun convertIntToTime(hours: Int, minutes: Int): String {
        val time = LocalTime(hour = hours, minute = minutes)
        return time.toString()
    }

    fun isValidDateTime(combinedDateTime: Long): Boolean {
        val now = Clock.System.now()
        val selectedInstant = Instant.fromEpochMilliseconds(combinedDateTime)
        val selectedLocalDateTime = selectedInstant.toLocalDateTime(TimeZone.currentSystemDefault())
        // Проверка, находится ли выбранная дата в прошлом
        return selectedLocalDateTime > now.toLocalDateTime(TimeZone.currentSystemDefault())
    }
}