package com.ideaapp.utils

import android.app.PendingIntent
import com.ideasapp.domain.utils.CustomNotificationChannel

interface ManagerNotification {

    fun init()

    fun post(
        id: Int,
        title: String,
        text: String,
        pendingIntent: PendingIntent,
        channel: CustomNotificationChannel
    )

    fun cancel(id: Int)


}

fun ManagerNotification.postReminderNotification(
    id: Int = (Int.MIN_VALUE..Int.MAX_VALUE).random(),
    title: String = "",
    body: String = "",
    pendingIntent: PendingIntent,
) {
    post(
        id = id,
        title = title,
        text = body,
        pendingIntent = pendingIntent,
        channel = CustomNotificationChannel.Reminders,
    )
}
