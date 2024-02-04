package com.ideaapp.presentation.screens.note.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.usecases.note.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>>
        get() = _notes



    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            try {
                getNoteUseCase.invoke().collect { notesList ->
                    _notes.value = notesList
                }
            } catch (e: Exception) {
                // Handle the exception if needed
                Log.e("MainViewModel", "Error loading notes", e)
            }
        }
    }
}
