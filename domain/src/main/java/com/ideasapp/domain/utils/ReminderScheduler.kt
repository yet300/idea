package com.ideasapp.domain.utils

import com.ideasapp.domain.model.Reminder

interface ReminderScheduler {
    suspend fun scheduleAlarm(reminder: Reminder)

    suspend fun cancelAlarm(reminder: Reminder)
}

