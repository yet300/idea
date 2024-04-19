package com.ideaapp.ui.screens.note.secure


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasapp.domain.model.Note
import com.ideasapp.domain.usecase.note.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteSecureViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>>
        get() = _notes

    init {
        getPrivateNotes()
    }

    private fun getPrivateNotes() {
        viewModelScope.launch {
            getNoteUseCase.invoke().collect { allNotes ->
                val privateNotes = allNotes.filter { it.isPrivate }
                _notes.value = privateNotes
            }
        }
    }
}