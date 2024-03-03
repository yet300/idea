package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.model.Note
import com.ideasapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetNoteUseCaseTest {

    private val noteRepository = mock<NoteRepository>()
    private val useCase = GetNoteUseCase(noteRepository)

    @Test
    fun `test GetNoteUseCase`() {

        val testNotes: Flow<List<Note>> = flowOf(
            listOf(
                Note(
                    id = 0,
                    title = "Test",
                    content = null
                ),
                Note(
                    id = 1,
                    title = "Test2",
                    content = null
                )
            )
        )


        Mockito.`when`(noteRepository.getNotes()).thenReturn(testNotes)
        val actual = useCase.invoke()
        assertEquals(testNotes, actual)

    }
}