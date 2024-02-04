package com.ideaapp.domain.usecases.task

import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) =
        taskRepository.updateTask(task)
}