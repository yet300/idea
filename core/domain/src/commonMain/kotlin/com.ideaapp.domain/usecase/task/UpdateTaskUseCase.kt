package com.ideaapp.domain.usecase.task

import com.ideaapp.domain.repository.TaskRepository

class UpdateTaskUseCase (private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: Long, complete: Boolean) =
        taskRepository.updateTask(id, complete)
}