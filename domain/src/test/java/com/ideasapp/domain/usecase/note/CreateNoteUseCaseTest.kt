package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.model.Note
import com.ideasapp.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class CreateNoteUseCaseTest {

    private val noteRepository = mock<NoteRepository>()

    @Test
    fun `test CreateNoteUseCase`() {


        val useCase = CreateNoteUseCase(noteRepository = noteRepository)

        val testNote = Note(
            id = 0,
            title = "Test",
            content = null
        )

        runBlocking {
            Mockito.`when`(noteRepository.insertNote(testNote)).thenAnswer { Note(id = 0, title = "Test", content = null) }

        }

        val actual = runBlocking { useCase.invoke(testNote) }

        assertNotNull(actual)
    }
}