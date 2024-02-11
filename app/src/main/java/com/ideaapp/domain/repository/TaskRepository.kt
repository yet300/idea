package com.ideaapp.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ideaapp.domain.model.Task
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskRepository {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE task SET isComplete = :complete WHERE id = :id")
    suspend fun updateTask(id: Long, complete: Boolean = true)
}