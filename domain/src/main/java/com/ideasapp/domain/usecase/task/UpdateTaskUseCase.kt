package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.repository.TaskRepository

class UpdateTaskUseCase (private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: Long, complete: Boolean) =
        taskRepository.updateTask(id, complete)
}