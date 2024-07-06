package com.ideaapp.shared.note.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ideaapp.shared.note.list.NoteListComponent


interface NoteComponent {
    val noteChild: Value<ChildStack<*, NoteChild>>

    val openCreteEdit: (Long?) -> Unit

    sealed interface NoteChild {
        class ListChild(val component: NoteListComponent) : NoteChild
    }
}