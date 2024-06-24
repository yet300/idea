package com.ideaapp.domain.note

import com.ideaapp.domain.model.Note
import com.ideaapp.domain.repository.NoteRepository
import com.ideaapp.domain.usecase.note.GetNoteByIdUseCase
import io.mockative.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

//class GetNoteByIdUseCaseTest {
//
//    private val noteRepository = mock<NoteRepository>()
//    private val useCase = GetNoteByIdUseCase(noteRepository)
//
//    @Test
//    fun `test GetNoteById`() {
//        val testNote = Note(
//            id = 0,
//            title = "Test0"
//        )
//
//
//        runBlocking {
//            mockito.`when`(noteRepository.getNoteById(testNote.id)).thenReturn(testNote)
//
//            val actual = useCase.invoke(testNote.id)
//
//            assertEquals(testNote, actual)
//        }
//    }
//}