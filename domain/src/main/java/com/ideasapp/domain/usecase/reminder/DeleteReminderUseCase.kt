package com.ideasapp.domain.usecase.reminder

import com.ideasapp.domain.repository.ReminderRepository
import com.ideasapp.domain.repository.ReminderSchedulerRepository
import jakarta.inject.Inject

class DeleteReminderUseCase @Inject constructor(
    private val reminderRepository: ReminderRepository,
    private val reminderScheduler: ReminderSchedulerRepository,
) {
    suspend operator fun invoke(id: Long) {
        val reminder = reminderRepository.getReminderById(id) ?: return
        reminderRepository.deleteReminder(reminder)
        reminderScheduler.cancelAlarm(reminder)
    }
}