package com.ideasapp.domain.utils

enum class CustomNotificationChannel(
    val id: String,
    val title: String,
    val importance: Int
) {
    REMINDERS(
        id = "reminder_channel",
        title = "Reminders",
        importance = 3
    );

    companion object {
        fun asList(): List<CustomNotificationChannel> {
            return listOf(
                REMINDERS
            )
        }
    }
}


