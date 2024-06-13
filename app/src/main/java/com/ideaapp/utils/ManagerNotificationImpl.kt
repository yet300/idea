package com.ideaapp.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.ideaapp.R
import com.ideasapp.domain.utils.CustomNotificationChannel

class ManagerNotificationImpl(
    private val context: Context,
) : ManagerNotification {

    private val notificationManager by lazy { context.getSystemService(NotificationManager::class.java) }

    override fun init() {
        val notificationChannels = getNotificationChannels()
        notificationChannels.forEach { channel ->
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun post(
        id: Int,
        name: String,
        description: String,
        pendingIntent: PendingIntent,
        channel: CustomNotificationChannel
    ) {
        val groupNotification = buildNotificationGroup(channel)

        val taskNotification = buildNotification(
            name,
            description,
            channel
        )

        NotificationManagerCompat.from(context).apply {
            notificationManager.notify(
                1,
                groupNotification.build()
            )
            notificationManager.notify(id, taskNotification)
        }


    }

    override fun cancel(id: Int) {
        notificationManager.cancel(id)
    }

    private fun buildNotification(
        title: String,
        text: String,
        channel: CustomNotificationChannel,
    ): Notification {
        return Notification
            .Builder(context, channel.id)
            .setSmallIcon(R.drawable.baseline_tips_monochrome)
            .setContentTitle(title)
            .setContentText(text)
            .setShowWhen(true)
            .setWhen(System.currentTimeMillis())
            .setGroup("com.android.example.TASK")
            .build()
    }

    private fun buildNotificationGroup(
        channel: CustomNotificationChannel,
    ): Notification.Builder {
        return Notification
            .Builder(context, channel.id)
            .setContentTitle(context.getString(R.string.Task))
            .setSmallIcon(R.drawable.baseline_task_24)
            .setGroup("com.android.example.TASK")
            .setGroupSummary(true)
    }

    private fun getNotificationChannels(): List<NotificationChannel> {
        return CustomNotificationChannel.asList()
            .map { channel ->
                val channelInfo = channel
                NotificationChannel(
                    channelInfo.id,
                    channelInfo.title,
                    channelInfo.importance,
                )
                    .apply {
                        description = "Task Description"
                    }
            }
    }

}

