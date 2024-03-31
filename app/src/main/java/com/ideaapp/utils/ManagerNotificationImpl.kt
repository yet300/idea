package com.ideaapp.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import  android.app.NotificationManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ideaapp.R
import com.ideasapp.domain.utils.CustomNotificationChannel

class ManagerNotificationImpl @Inject constructor(
    @ApplicationContext private val context: Context,
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
        title: String,
        text: String,
        pendingIntent: PendingIntent,
        channel: CustomNotificationChannel
    ) {
        val groupNotification = buildNotificationGroup(channel)

        val taskNotification = buildNotification(
            title,
            text,
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
        body: String,
        channel: CustomNotificationChannel,
    ): Notification {
        return Notification
            .Builder(context, channel.info.id)
            .setSmallIcon(R.drawable.baseline_tips_monochrome)
            .setContentTitle(title)
            .setContentText(body)
            .setShowWhen(true)
            .setWhen(System.currentTimeMillis())
            .setGroup("com.android.example.TASK")
            .build()
    }

    private fun buildNotificationGroup(
        channel: CustomNotificationChannel,
    ): NotificationCompat.Builder {
        return NotificationCompat
            .Builder(context, channel.info.id)
            .setContentTitle(context.getString(R.string.Task))
            .setSmallIcon(R.drawable.baseline_task_24)
            .setGroup("com.android.example.TASK")
            .setGroupSummary(true)
    }

    private fun getNotificationChannels(): List<NotificationChannel> {
        return CustomNotificationChannel.asList()
            .map { channel ->
                val channelInfo = channel.info
                NotificationChannel(
                    channelInfo.id,
                    channelInfo.name,
                    channelInfo.importance,
                )
                    .apply {
                        description = "Task Description"
                    }
            }
    }

}

