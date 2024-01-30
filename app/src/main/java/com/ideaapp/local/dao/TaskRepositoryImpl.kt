package com.ideaapp.local.dao


import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun getTasks(): List<Task> = taskRepository.getTasks()

    suspend fun insertTask(task: Task) =
        taskRepository.insertTask(task)

    suspend fun deleteTask(task: Task) = taskRepository.deleteTask(task)

}