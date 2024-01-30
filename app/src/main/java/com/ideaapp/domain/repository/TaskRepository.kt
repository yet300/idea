package com.ideaapp.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ideaapp.domain.model.Task


@Dao
interface TaskRepository {
    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Delete
    suspend fun deleteTask(task: Task)

}