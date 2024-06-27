package com.ideaapp.shared.note.create_edit

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.usecase.note.CreateNoteUseCase
import com.ideaapp.domain.usecase.note.DeleteNoteUseCase
import com.ideaapp.domain.usecase.note.GetNoteByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class DefaultNoteCreateEditComponent(
    private val createNoteUseCase: CreateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val onBack: (Note?) -> Unit,
    private val item: Note
) : NoteCreateEditComponent {
    private val _model = MutableValue(NoteCreateEditComponent.Model(item = item))

    override val model: Value<NoteCreateEditComponent.Model> = _model

    init {
        CoroutineScope(Dispatchers.IO).launch {
//            createNoteUseCase.invoke(
//                _model.value.item.copy(
//
//                )
//            )
        }
    }

    override fun onBackClick(item: Note?) {
        onBack(item)
    }

    override fun delete(item: Note) {
        TODO("Not yet implemented")
    }

    override fun updateTitle(title: String) {
        TODO("Not yet implemented")
    }

    override fun updateContent(content: String) {
        TODO("Not yet implemented")
    }

    override fun updatePrivate(isPrivate: Boolean) {
        TODO("Not yet implemented")
    }

    override fun updateImage(image: String) {
        TODO("Not yet implemented")
    }
}