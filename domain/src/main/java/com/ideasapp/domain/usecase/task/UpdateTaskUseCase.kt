package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.repository.TaskRepository
import jakarta.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: Long, complete: Boolean) =
        taskRepository.updateTask(id, complete)
}