package com.ideaapp.presentation.screens.secure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.usecases.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecureViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    init {
        getPrivateNotes()
    }

    private fun getPrivateNotes() {
        viewModelScope.launch {
            getNoteUseCase.invoke().let { allNotes ->
                val privateNotes = allNotes.filter { it.isPrivate }
                _notes.postValue(privateNotes)
            }
        }
    }
}