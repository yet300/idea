package com.ideaapp.ui.screens.note.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasapp.domain.model.Note
import com.ideasapp.domain.usecase.note.GetNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel (
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>>
        get() = _notes

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText


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
                Log.e("NoteViewModel", "Error loading notes", e)
            }
        }
    }

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun performSearch() {
        val searchTextValue = _searchText.value
        _notes.value = _notes.value.filter { note ->
            !note.isPrivate && note.title.contains(searchTextValue, ignoreCase = true)
        }
    }
}
