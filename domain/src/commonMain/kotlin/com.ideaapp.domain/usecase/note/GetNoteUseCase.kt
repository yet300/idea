package com.ideaapp.domain.usecase.note

import com.ideaapp.domain.repository.NoteRepository

class GetNoteUseCase (private val noteRepository: NoteRepository) {
    operator fun invoke() = noteRepository.getNotes()
}