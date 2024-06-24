package com.ideaapp.domain.repository

import com.ideaapp.domain.model.Reminder


interface ReminderSchedulerRepository {
    suspend fun scheduleAlarm(reminder: Reminder)

    suspend fun cancelAlarm(reminder: Reminder)
}