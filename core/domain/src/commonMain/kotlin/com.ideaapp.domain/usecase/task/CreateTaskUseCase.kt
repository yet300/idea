package com.ideaapp.domain.usecase.task

import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.TaskRepository

class CreateTaskUseCase (private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) = taskRepository.insertTask(task)
}