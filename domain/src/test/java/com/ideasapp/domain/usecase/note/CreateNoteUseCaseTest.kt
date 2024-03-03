package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.model.Note
import com.ideasapp.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CreateNoteUseCaseTest {

    private val noteRepository = mock<NoteRepository>()
    private val useCase = CreateNoteUseCase(noteRepository = noteRepository)

    @Test
    fun `test CreateNoteUseCase`() {


        val testNote = Note(
            id = 0,
            title = "Test",
            content = null
        )

        runBlocking {
            useCase.invoke(testNote)
            verify(noteRepository).insertNote(testNote)

        }


    }
}