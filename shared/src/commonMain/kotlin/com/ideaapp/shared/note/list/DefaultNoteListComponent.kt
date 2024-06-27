package com.ideaapp.shared.note.list

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.usecase.note.GetNoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class DefaultNoteListComponent(
    private val getNoteUseCase: GetNoteUseCase,
    private val noteClicked: (item: Note) -> Unit,
) : NoteListComponent {

    private val _model =
        MutableValue(NoteListComponent.Model(items = emptyList()))
    override val model: Value<NoteListComponent.Model> = _model

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getNoteUseCase.invoke().collect {
                _model.value = NoteListComponent.Model(items = it)
            }
        }
    }

    private var _setSerch = ""


    override fun onItemClicked(item: Note) {
        noteClicked(item)
    }

    override fun setSearchText(text: String) {
        _setSerch = text
    }

}