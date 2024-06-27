package com.ideasapp.data.repository

import com.ideaapp.domain.model.Reminder
import com.ideaapp.domain.repository.ReminderRepository
import com.ideasapp.data.dao.ReminderDAO
import com.ideasapp.data.wrapper.toDomainReminder
import com.ideasapp.data.wrapper.toRoomReminder

class ReminderRepositoryImpl(
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