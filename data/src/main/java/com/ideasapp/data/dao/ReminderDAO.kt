package com.ideasapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ideasapp.data.model.ReminderDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderDBO): Long

    @Query(
        """SELECT * FROM reminder
                WHERE id = :id
                """
    )
    suspend fun getReminderById(id: Long): ReminderDBO?


    @Query(
        " SELECT * FROM reminder"
    )
    fun getReminders(): Flow<List<ReminderDBO>>

    @Delete
    suspend fun deleteReminder(reminder: ReminderDBO)

    @Query("DELETE FROM reminder WHERE itemId = :id")
    suspend fun deleteReminderByItemId(id: Long)
}