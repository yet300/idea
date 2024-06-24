package com.ideaapp.domain.usecase.note


import com.ideaapp.domain.model.Note
import com.ideaapp.domain.repository.NoteRepository

class DeleteNoteUseCase (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.deleteNote(note = note)
}