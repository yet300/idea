package com.ideasapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ideasapp.data.model.NoteDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteDBO)

    @Query("SELECT * FROM note")
    fun getNotes():Flow<List<NoteDBO>>

    @Delete
    suspend fun deleteNote(note: NoteDBO)


    @Query("SELECT * FROM note WHERE id=:nodeId")
    suspend fun getNoteById(nodeId: Long): NoteDBO

}