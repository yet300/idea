package com.ideaapp.local.dao


import com.ideaapp.domain.model.Note
import com.ideaapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteRepositoryImpl: NoteRepository) {

    fun getNotes(): Flow<List<Note>> = noteRepositoryImpl.getNotes()
    suspend fun insertNote(note: Note) = noteRepositoryImpl.insertNote(note = note)
    suspend fun deleteNote(note: Note) = noteRepositoryImpl.deleteNote(note = note)
    suspend fun getNoteById(id: Long) = noteRepositoryImpl.getNoteById(nodeId = id)
}