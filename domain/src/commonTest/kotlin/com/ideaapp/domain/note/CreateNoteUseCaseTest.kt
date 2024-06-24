package com.ideaapp.domain.note

//import com.ideaapp.domain.model.Note
//import com.ideaapp.domain.repository.NoteRepository
//import com.ideaapp.domain.usecase.note.CreateNoteUseCase
//import io.mockative.Mock
//import io.mockative.classOf
//import io.mockative.mock
//import io.mockative.verify
//import kotlinx.coroutines.runBlocking
//import kotlin.test.Test
//
//class CreateNoteUseCaseTest {
//
//    @Mock
//    private val noteRepository = mock(classOf<NoteRepository>())
//
//    private val useCase = CreateNoteUseCase(noteRepository = noteRepository)
//
//    @Test
//    fun `test CreateNoteUseCase`() {
//
//
//        val testNote = Note(
//            id = 0,
//            title = "Test",
//            content = null
//        )
//
//
//        runBlocking {
//            useCase.invoke(testNote)
////            verify(noteRepository).insertNote(testNote)
//
//            verify { noteRepository.insertNote(testNote) }
//
//        }
//
//    }
//}