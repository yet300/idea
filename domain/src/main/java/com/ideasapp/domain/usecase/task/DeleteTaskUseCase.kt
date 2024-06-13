package com.ideasapp.domain.usecase.task


import com.ideasapp.domain.model.Task
import com.ideasapp.domain.repository.ReminderRepository
import com.ideasapp.domain.repository.TaskRepository

class DeleteTaskUseCase (
    private val taskRepository: TaskRepository,
    private val reminderRepository: ReminderRepository,
) {

    suspend operator fun invoke(task: Task) {
        taskRepository.deleteTask(task)
        reminderRepository.deleteReminderByItemId(task.id)
    }
}