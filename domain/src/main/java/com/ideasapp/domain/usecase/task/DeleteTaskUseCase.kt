package com.ideasapp.domain.usecase.task


import com.ideasapp.domain.model.Task
import com.ideasapp.domain.repository.TaskRepository

class DeleteTaskUseCase (private val taskRepository: TaskRepository) {

    suspend operator fun invoke(task: Task) = taskRepository.deleteTask(task)
}