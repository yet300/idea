package com.ideaapp.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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


    @Update
    suspend fun updateTask(task: Task)
}