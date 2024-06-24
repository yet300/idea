package com.ideaapp.domain.note

//import com.ideaapp.domain.model.Note
//import com.ideaapp.domain.repository.NoteRepository
//import com.ideaapp.domain.usecase.note.GetNoteUseCase
//import io.mockative.mock
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flowOf
//import kotlin.test.Test
//import kotlin.test.assertEquals
//
//class GetNoteUseCaseTest {
//
//    private val noteRepository = mock<NoteRepository>()
//    private val useCase = GetNoteUseCase(noteRepository)
//
//    @Test
//    fun `test GetNoteUseCase`() {
//
//        val testNotes: Flow<List<Note>> = flowOf(
//            listOf(
//                Note(
//                    id = 0,
//                    title = "Test",
//                    content = null
//                ),
//                Note(
//                    id = 1,
//                    title = "Test2",
//                    content = null
//                )
//            )
//        )
//
//        Mockito.`when`(noteRepository.getNotes()).thenReturn(testNotes)
//        val actual = useCase.invoke()
//        assertEquals(testNotes, actual)
//
//    }
//}