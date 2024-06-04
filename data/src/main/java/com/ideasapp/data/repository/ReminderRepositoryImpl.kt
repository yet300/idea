package com.ideasapp.data.repository

import com.ideasapp.data.dao.ReminderDAO
import com.ideasapp.data.wrapper.toDomainReminder
import com.ideasapp.data.wrapper.toRoomReminder
import com.ideasapp.domain.model.Reminder
import com.ideasapp.domain.repository.ReminderRepository
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val dao: ReminderDAO
) : ReminderRepository {
    override suspend fun insertReminder(reminder: Reminder): Long {
        return dao.insertReminder(reminder.toRoomReminder())
    }

    override suspend fun getReminderById(id: Long): Reminder? {
        return dao.getReminderById(id)?.toDomainReminder()
    }

    override suspend fun getReminderByItemId(itemId: Long): Reminder? {
        return dao.getReminderByItemId(itemId)?.toDomainReminder()
    }


    override suspend fun deleteReminder(reminder: Reminder) {
        return dao.deleteReminder(reminder.toRoomReminder())
    }

    override suspend fun deleteReminderByItemId(id: Long) {
        return dao.deleteReminderByItemId(id)
    }


}