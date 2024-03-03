package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.model.Note
import com.ideasapp.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetNoteByIdUseCaseTest {

    private val noteRepository = mock<NoteRepository>()
    private val useCase = GetNoteByIdUseCase(noteRepository)

    @Test
    fun `test GetNoteById`() {
        val testNote = Note(
            id = 0,
            title = "Test0"
        )


        runBlocking {
            Mockito.`when`(noteRepository.getNoteById(testNote.id)).thenReturn(testNote)

            val actual = useCase.invoke(testNote.id)

            assertEquals(testNote, actual)
        }
    }
}