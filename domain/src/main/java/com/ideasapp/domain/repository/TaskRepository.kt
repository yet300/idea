package com.ideasapp.domain.repository


import com.ideasapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Long): Task?
    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun updateTask(id: Long, complete: Boolean = true)
}