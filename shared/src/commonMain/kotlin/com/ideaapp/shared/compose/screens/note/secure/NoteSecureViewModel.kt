package com.ideaapp.shared.compose.screens.note.secure


//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.ideasapp.domain.model.Note
//import com.ideasapp.domain.usecase.note.GetNoteUseCase
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//
//class NoteSecureViewModel(
//    private val getNoteUseCase: GetNoteUseCase
//) : ViewModel() {
//    private val _notes = MutableStateFlow<List<Note>>(emptyList())
//    val notes: StateFlow<List<Note>>
//        get() = _notes
//
//    init {
//        getPrivateNotes()
//    }
//
//    private fun getPrivateNotes() {
//        viewModelScope.launch {
//            getNoteUseCase.invoke().collect { allNotes ->
//                val privateNotes = allNotes.filter { it.isPrivate }
//                _notes.value = privateNotes
//            }
//        }
//    }
//}