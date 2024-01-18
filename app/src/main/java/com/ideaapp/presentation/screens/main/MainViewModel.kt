package com.ideaapp.presentation.screens.main

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
class MainViewModel @Inject constructor(
    private val getAllNoteUseCase: GetNoteUseCase
) : ViewModel() {
    private val _notes = MutableLiveData<List<Note>>()


    val notes: LiveData<List<Note>>
        get() = _notes

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNoteUseCase.invoke().let {
                _notes.postValue(it)
            }
        }
    }


}