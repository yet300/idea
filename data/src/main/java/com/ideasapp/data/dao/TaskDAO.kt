package com.ideasapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ideasapp.data.model.TaskDBO
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<TaskDBO>>

    @Insert
    suspend fun insertTask(task: TaskDBO)

    @Delete
    suspend fun deleteTask(task: TaskDBO)

    @Query("UPDATE task SET isComplete = :complete WHERE id = :id")
    suspend fun updateTask(id: Long, complete: Boolean = true)
}