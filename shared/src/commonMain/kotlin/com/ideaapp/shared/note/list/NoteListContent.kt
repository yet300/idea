package com.ideaapp.shared.note.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.ideaapp.shared.note.list.components.EmptyScreen
import com.ideaapp.shared.note.list.components.ListComponent


@Composable
fun NoteListContent(
    component: NoteListComponent,
    modifier: Modifier = Modifier
) {
    val notes by component.model.subscribeAsState()

    if (notes.items.isNotEmpty()) {
        ListComponent(
            queryNotes = notes.items,
            component = component,
        )
    } else {
        EmptyScreen()
    }
}