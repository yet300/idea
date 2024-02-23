package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.repository.NoteRepository

class GetNoteUseCase (private val noteRepository: NoteRepository) {
    operator fun invoke() = noteRepository.getNotes()
}