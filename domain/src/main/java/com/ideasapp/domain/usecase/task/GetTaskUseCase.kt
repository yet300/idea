package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.repository.TaskRepository
import jakarta.inject.Inject

class GetTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.getTasks()
}