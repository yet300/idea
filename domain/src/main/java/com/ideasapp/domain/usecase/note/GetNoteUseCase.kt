package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.repository.NoteRepository
import jakarta.inject.Inject

class GetNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke() = noteRepository.getNotes()
}