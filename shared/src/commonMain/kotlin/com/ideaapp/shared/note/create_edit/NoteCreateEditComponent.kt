package com.ideaapp.shared.note.create_edit

import com.arkivanov.decompose.value.Value
import com.ideaapp.domain.model.Note

interface NoteCreateEditComponent {
    val model: Value<Model>

    data class Model(val item: Note)

    fun onBackClick(item: Note?)
    fun delete(item: Note)


    fun updateTitle(title: String)
    fun updateContent(content: String)
    fun updatePrivate(isPrivate: Boolean)
    fun updateImage(image: String)
}