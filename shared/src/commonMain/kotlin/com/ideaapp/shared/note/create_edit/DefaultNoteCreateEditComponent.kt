package com.ideaapp.shared.note.create_edit

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.usecase.note.CreateNoteUseCase
import com.ideaapp.domain.usecase.note.DeleteNoteUseCase
import com.ideaapp.domain.usecase.note.GetNoteByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer

class DefaultNoteCreateEditComponent(
    private val createNoteUseCase: CreateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val onBack: () -> Unit,
    stateKeeper: StateKeeper
) : NoteCreateEditComponent {
    private val _model = MutableValue(Note())

    override val model: Value<Note>
        get() = _model

    override fun onBackClick() {
        onBack()
    }

    override fun delete(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteNoteUseCase.invoke(Note(id = id))
            onBackClick()
        }
    }

    private var currentNoteId: Long? = null


    init {
        //TODO FIX LATER
        stateKeeper.consume("noteId", strategy = Long.serializer())?.also { noteId ->
            if (noteId != 0L) {
                CoroutineScope(Dispatchers.IO).launch {
                    getNoteByIdUseCase.invoke(noteId)?.also { note ->
                        currentNoteId = note.id

                        _model.value = model.value.copy(
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

    override fun updateTitle(title: String) {
        _model.value = _model.value.copy(title = title)
    }

    override fun updateContent(content: String) {
        _model.value = _model.value.copy(content = content)
    }

    override fun updatePrivate(isPrivate: Boolean) {
        _model.value = _model.value.copy(isPrivate = isPrivate)
    }

    override fun updateImage(image: String) {
        _model.value = _model.value.copy(imageUri = image)
    }
}