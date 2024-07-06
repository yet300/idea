package com.ideaapp.shared.note.list

import com.arkivanov.decompose.value.Value
import com.ideaapp.domain.model.Note

interface NoteListComponent {
    val model: Value<Model>

    data class Model(
        val items: List<Note>
    )

    fun onItemClicked(item: Long)

    fun setSearchText(text: String)
}