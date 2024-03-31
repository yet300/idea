package com.ideaapp.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.ideaapp.component.broadcast.NotificationReceiver
import com.ideasapp.domain.model.Reminder
import com.ideasapp.domain.utils.Arguments
import com.ideasapp.domain.utils.ReminderScheduler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ReminderSchedulerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ReminderScheduler {
    private val alarmManager by lazy { context.getSystemService(AlarmManager::class.java) }

    override suspend fun scheduleAlarm(
        reminder: Reminder
    ) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            reminder.reminderTime,
            buildPendingIntent(reminder),
        )
    }

    override suspend fun cancelAlarm(reminder: Reminder) {
        alarmManager.cancel(buildPendingIntent(reminder))
    }

    private fun buildPendingIntent(reminder: Reminder): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            reminder.id?.toInt() ?: 0,
            buildIntent(reminder),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun buildIntent(reminder: Reminder): Intent {
        return Intent(context, NotificationReceiver::class.java).apply {
            putExtras(
                bundleOf(
                    Arguments.ReminderId.key to reminder.id,
                    Arguments.Message.key to reminder.name,
                    Arguments.ItemId.key to reminder.itemId,
                ),
            )
        }
    }
}
