package com.ideaapp.domain.note

//import com.ideaapp.domain.model.Note
//import com.ideaapp.domain.repository.NoteRepository
//import com.ideaapp.domain.usecase.note.DeleteNoteUseCase
//import io.mockative.mock
//import kotlinx.coroutines.runBlocking
//import kotlin.test.Test
//import kotlin.test.assertEquals
//
//class DeleteNoteUseCaseTest {
//
//    private val noteRepository = mock<NoteRepository>()
//
//    @Test
//    fun `test DeleteNote`() {
//        val useCase = DeleteNoteUseCase(noteRepository)
//
//        val testNote = Note(
//            id = 0,
//            title = "Test0",
//            content = null
//        )
//
//        runBlocking {
//            //удаление заметки из репозитория
//            `when`(noteRepository.deleteNote(testNote)).thenReturn(Unit)
//            val actual = useCase.invoke(testNote)
//
//            // Проверяем, что функция удаления возвращает null
//            assertEquals(Unit, actual)
//
//        }
//    }
//}