package com.ideaapp.shared.note.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.ideaapp.shared.note.list.components.EmptyScreen
import com.ideaapp.shared.note.list.components.ListComponent


@Composable
fun NoteListContent(
    component: NoteListComponent,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val notes by component.model.subscribeAsState()

    if (notes.items.isNotEmpty()) {
        ListComponent(
            queryNotes = notes.items,
            component = component,
            paddingValues = paddingValues,
        )
    } else {
        EmptyScreen()
    }
}