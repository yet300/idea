package com.ideaapp.ui.screens.note.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.usecases.note.CreateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {
    fun createNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch {
            createNoteUseCase.invoke(note = note)
            onSuccess()
        }
    }
}