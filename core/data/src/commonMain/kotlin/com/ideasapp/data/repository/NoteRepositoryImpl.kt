package com.ideasapp.data.repository

import com.ideaapp.domain.model.Note
import com.ideaapp.domain.repository.NoteRepository
import com.ideasapp.data.dao.NoteDAO
import com.ideasapp.data.wrapper.toDomainNote
import com.ideasapp.data.wrapper.toRoomNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl (private val dao: NoteDAO) : NoteRepository {
    override suspend fun insertNote(note: Note) {
         dao.insertNote(note.toRoomNote())
    }

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { list -> list.map { it.toDomainNote() } }
    }

    override suspend fun getNoteById(nodeId: Long): Note? {
        return dao.getNoteById(nodeId)?.toDomainNote()
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toRoomNote())
    }


}