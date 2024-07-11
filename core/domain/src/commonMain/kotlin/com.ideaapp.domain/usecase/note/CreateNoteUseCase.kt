package com.ideaapp.domain.usecase.note

import com.ideaapp.domain.model.Note
import com.ideaapp.domain.repository.NoteRepository

class CreateNoteUseCase (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.insertNote(note = note)
}