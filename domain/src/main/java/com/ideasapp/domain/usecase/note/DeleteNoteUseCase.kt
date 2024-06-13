package com.ideasapp.domain.usecase.note


import com.ideasapp.domain.model.Note
import com.ideasapp.domain.repository.NoteRepository

class DeleteNoteUseCase (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.deleteNote(note = note)
}