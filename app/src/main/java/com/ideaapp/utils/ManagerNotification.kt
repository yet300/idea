package com.ideaapp.utils

import android.app.PendingIntent
import com.ideasapp.domain.utils.CustomNotificationChannel

interface ManagerNotification {

    fun init()

    fun post(
        id: Int,
        name: String,
        description: String,
        pendingIntent: PendingIntent,
        channel: CustomNotificationChannel
    )

    fun cancel(id: Int)


}

fun ManagerNotification.postReminderNotification(
    id: Int = (Int.MIN_VALUE..Int.MAX_VALUE).random(),
    name: String,
    description: String?,
    pendingIntent: PendingIntent,
) {
    post(
        id = id,
        name = name,
        description = description ?: "",
        pendingIntent = pendingIntent,
        channel = CustomNotificationChannel.REMINDERS,
    )
}
