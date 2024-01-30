package com.ideaapp.domain.usecases.note

import com.ideaapp.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getNotes()
}