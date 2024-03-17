package com.ideaapp.ui.notification

interface ReminderNotification {

    suspend fun setReminder(id: Long, reminderTime: Long, name: String, description: String)
    suspend fun cancelReminder(id: Long)
}

