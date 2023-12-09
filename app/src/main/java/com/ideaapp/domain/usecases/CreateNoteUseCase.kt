package com.ideaapp.domain.usecases

import com.ideaapp.domain.model.Note
import com.ideaapp.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.insertNote(note = note)
}