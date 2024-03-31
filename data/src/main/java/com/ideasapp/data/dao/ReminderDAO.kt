package com.ideasapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ideasapp.data.model.ReminderDBO

@Dao
interface ReminderDAO {

    @Insert
    suspend fun insertReminder(reminder: ReminderDBO): Long

    @Query(
        """SELECT * FROM reminder
                WHERE id = :id
                """
    )
    suspend fun getReminderById(id: Long): ReminderDBO?


    @Delete
    suspend fun deleteReminder(reminder: ReminderDBO)

    @Query("DELETE FROM reminder WHERE itemId = :id")
    suspend fun deleteReminderByItemId(id: Long)
}