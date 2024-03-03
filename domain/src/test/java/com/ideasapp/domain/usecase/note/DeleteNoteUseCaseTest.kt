package com.ideasapp.domain.usecase.note

import com.ideasapp.domain.model.Note
import com.ideasapp.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class DeleteNoteUseCaseTest {

    private val noteRepository = mock<NoteRepository>()

    @Test
    fun `test DeleteNote`() {
        val useCase = DeleteNoteUseCase(noteRepository)

        val testNote = Note(
            id = 0,
            title = "Test0",
            content = null
        )

        runBlocking {
            //удаление заметки из репозитория
            Mockito.`when`(noteRepository.deleteNote(testNote)).thenReturn(Unit)

            val actual = useCase.invoke(testNote)

            // Проверяем, что функция удаления возвращает null
            Assertions.assertEquals(Unit, actual)
        }
    }
}