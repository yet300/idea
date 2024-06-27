package com.ideaapp.domain.repository

import com.ideaapp.domain.model.Reminder

interface ReminderRepository {

    suspend fun insertReminder(reminder: Reminder): Long

    suspend fun getReminderById(id: Long): Reminder?

    suspend fun getReminderByItemId(itemId:Long):Reminder?

    suspend fun deleteReminder(reminder: Reminder)

    suspend fun deleteReminderByItemId(id: Long)


}