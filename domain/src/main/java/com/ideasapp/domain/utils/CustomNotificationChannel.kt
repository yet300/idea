package com.ideasapp.domain.utils

import com.ideasapp.domain.model.NotificationChannelInfo

sealed class CustomNotificationChannel(val info: NotificationChannelInfo) {

    data object Reminders : CustomNotificationChannel(
        NotificationChannelInfo(
            id = "reminder_channel",
            name = "Reminders",
            importance = 3
        )
    )
    companion object {
        fun asList(): List<CustomNotificationChannel> {
            return listOf(
                Reminders
            )
        }
    }
}


