package com.ideaapp.domain.usecase.task

import com.ideaapp.domain.repository.TaskRepository

class GetTaskByIdUseCase (private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: Long) = taskRepository.getTaskById(id)
}