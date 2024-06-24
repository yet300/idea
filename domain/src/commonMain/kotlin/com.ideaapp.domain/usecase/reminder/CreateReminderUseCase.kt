package com.ideaapp.domain.usecase.reminder

import com.ideaapp.domain.model.Reminder
import com.ideaapp.domain.repository.ReminderRepository
import com.ideaapp.domain.repository.ReminderSchedulerRepository

class CreateReminderUseCase (
    private val reminderRepository: ReminderRepository,
    private val reminderScheduler: ReminderSchedulerRepository
) {
    suspend operator fun invoke(reminder: Reminder) {
        val id = reminderRepository.insertReminder(reminder)
        reminderScheduler.scheduleAlarm(reminder.copy(id = id))
    }
}