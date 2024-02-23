package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.repository.TaskRepository

class GetTaskUseCase (private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.getTasks()
}