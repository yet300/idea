package com.ideaapp.domain.usecases.task

import com.ideaapp.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: Long, complete: Boolean) =
        taskRepository.updateTask(id,complete)
}