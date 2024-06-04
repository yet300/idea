package com.ideaapp.ui.screens.note.create_edit


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasapp.domain.model.Note
import com.ideasapp.domain.usecase.note.CreateNoteUseCase
import com.ideasapp.domain.usecase.note.DeleteNoteUseCase
import com.ideasapp.domain.usecase.note.GetNoteByIdUseCase
import com.ideasapp.domain.utils.InvalidException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteCreateEditViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _noteState = MutableStateFlow(Note())
    val noteState: StateFlow<Note>
        get() = _noteState


    private var currentNoteId: Long? = null


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        savedStateHandle.get<Long>("noteId")?.let { noteId ->
            if (noteId != -1L) {
                viewModelScope.launch {
                    getNoteByIdUseCase.invoke(noteId)?.also { note ->
                        currentNoteId = note.id

                        _noteState.value = noteState.value.copy(
                            title = note.title,
                            content = note.content,
                            imageUri = note.imageUri,
                            isPrivate = note.isPrivate
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteCreateEditUiEvent) {
        when (event) {
            NoteCreateEditUiEvent.Delete -> viewModelScope.launch {
                try {
                    deleteNoteUseCase.invoke(
                        Note(
                            id = currentNoteId ?: 0L
                        )
                    )
                    _eventFlow.emit(UiEvent.Delete)

                } catch (e: InvalidException) {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(
                            message = e.message ?: "Couldn't deleted "
                        )
                    )
                }
            }


            NoteCreateEditUiEvent.Save -> viewModelScope.launch {
                if (noteState.value.title.isNotEmpty()) {
                    createNoteUseCase.invoke(
                        Note(
                            id = currentNoteId ?: 0L,
                            title = noteState.value.title,
                            content = noteState.value.content,
                            imageUri = noteState.value.imageUri,
                            isPrivate = noteState.value.isPrivate,
                        )
                    )
                    _eventFlow.emit(UiEvent.Save)
                } else {
                    _eventFlow.emit(UiEvent.Delete)
                    deleteNoteUseCase.invoke(
                        Note(
                            id = currentNoteId ?: 0L
                        )
                    )
                }
            }

            is NoteCreateEditUiEvent.UpdateTitle -> {
                _noteState.value = _noteState.value.copy(
                    title = event.title
                )
            }

            is NoteCreateEditUiEvent.UpdateContent -> {
                _noteState.value = _noteState.value.copy(
                    content = event.content
                )
            }

            is NoteCreateEditUiEvent.UpdatePrivate -> {
                _noteState.value = _noteState.value.copy(
                    isPrivate = event.private
                )
            }

            is NoteCreateEditUiEvent.UpdateUrl -> {
                _noteState.value = _noteState.value.copy(
                    imageUri = event.url
                )
            }
        }

    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object Save : UiEvent()
        data object Delete : UiEvent()
    }
}
