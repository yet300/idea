package com.ideaapp.ui.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ReminderManager @Inject constructor(@ApplicationContext private val context: Context) :
    ReminderNotification {
    private val alarmManager by lazy { context.getSystemService(AlarmManager::class.java) }

    override suspend fun setReminder(
        id: Long?,
        reminderTime: Long,
        name: String,
        description: String,

        ) {
        val intent = Intent(context, NotificationService::class.java).apply {
            putExtra(NotificationService.TASK_ID, id)
            putExtra(NotificationService.TASK_NAME_KEY, name)
            putExtra(NotificationService.TASK_DESCRIPTION_KEY, description)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            reminderTime,
            pendingIntent(id, intent)
        )
    }

    override suspend fun cancelReminder(id: Long?) {
        val intent = Intent(context, NotificationService::class.java)

        pendingIntent(id, intent).let {
            alarmManager.cancel(it)
            it.cancel()
        }
    }
    private fun pendingIntent(
        id: Long?,
        intent: Intent
    ): PendingIntent = PendingIntent.getService(
        context,
        id?.toInt() ?: 0,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
}
