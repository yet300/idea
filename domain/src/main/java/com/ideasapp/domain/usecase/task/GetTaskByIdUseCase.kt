package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.repository.TaskRepository

class GetTaskByIdUseCase (private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: Long) = taskRepository.getTaskById(id)
}