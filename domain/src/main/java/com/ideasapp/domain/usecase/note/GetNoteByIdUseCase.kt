package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.repository.NoteRepository

class GetNoteByIdUseCase (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNoteById(nodeId = id)
}