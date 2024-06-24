package com.ideaapp.domain.repository


import com.ideaapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)

    fun getNotes(): Flow<List<Note>>

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(nodeId: Long): Note?

}