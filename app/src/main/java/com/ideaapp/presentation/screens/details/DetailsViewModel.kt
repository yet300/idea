package com.ideaapp.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.usecases.note.DeleteNoteUseCase
import com.ideaapp.domain.usecases.note.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note


    fun getNoteById(id: Long) {
        viewModelScope.launch {
            getNoteByIdUseCase.invoke(id = id).let {
                _note.postValue(it)
            }
        }
    }

    fun deleteNote(onSeccsess: () -> Unit) {
        viewModelScope.launch {
            note.value?.let {
                deleteNoteUseCase.invoke(note = it)
                onSeccsess()
            }

        }
    }

}