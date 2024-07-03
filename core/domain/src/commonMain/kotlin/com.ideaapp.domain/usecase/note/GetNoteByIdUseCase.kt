package com.ideaapp.domain.usecase.note

import com.ideaapp.domain.repository.NoteRepository

class GetNoteByIdUseCase (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNoteById(nodeId = id)
}