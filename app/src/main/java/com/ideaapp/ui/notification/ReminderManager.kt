package com.ideaapp.ui.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ReminderManager @Inject constructor(@ApplicationContext private val context: Context) :
    ReminderNotification {

    override suspend fun setReminder(
        id: Long,
        reminderTime: Long,
        name: String,
        description: String,

    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationService::class.java).apply {
            putExtra(NotificationService.TASK_ID, id)
            putExtra(NotificationService.TASK_NAME_KEY, name)
            putExtra(NotificationService.TASK_DESCRIPTION_KEY, description)
        }

        val pendingIntent = PendingIntent.getService(
            context,
            id.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            reminderTime,
            pendingIntent
        )
    }

    override suspend fun cancelReminder(id: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationService::class.java)
        val pendingIntent = PendingIntent.getService(
            context,
            id.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        pendingIntent?.let {
            alarmManager.cancel(it)
            it.cancel()
        }
    }
}
