package com.ideaapp.domain.usecase.task


import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.ReminderRepository
import com.ideaapp.domain.repository.TaskRepository

class DeleteTaskUseCase (
    private val taskRepository: TaskRepository,
    private val reminderRepository: ReminderRepository,
) {

    suspend operator fun invoke(task: Task) {
        taskRepository.deleteTask(task)
        reminderRepository.deleteReminderByItemId(task.id)
    }
}