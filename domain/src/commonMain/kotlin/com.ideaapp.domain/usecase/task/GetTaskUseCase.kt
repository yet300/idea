package com.ideaapp.domain.usecase.task

import com.ideaapp.domain.repository.TaskRepository

class GetTaskUseCase (private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.getTasks()
}