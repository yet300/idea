package com.ideasapp.domain.repository

import com.ideasapp.domain.model.Reminder

interface ReminderSchedulerRepository {
    suspend fun scheduleAlarm(reminder: Reminder)

    suspend fun cancelAlarm(reminder: Reminder)
}