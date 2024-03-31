package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.repository.NoteRepository
import jakarta.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNoteById(nodeId = id)
}