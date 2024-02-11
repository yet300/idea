package com.ideaapp.local.dao


import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskRepository: TaskRepository) {

    fun getTasks(): Flow<List<Task>> = taskRepository.getTasks()

    suspend fun insertTask(task: Task) =
        taskRepository.insertTask(task)

    suspend fun updateTask(id: Long, complete: Boolean) =
        taskRepository.updateTask(id, complete)


    suspend fun deleteTask(task: Task) = taskRepository.deleteTask(task)

}