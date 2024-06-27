package com.ideaapp.shared.note.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ideaapp.shared.note.create_edit.NoteCreateEditComponent
import com.ideaapp.shared.note.list.NoteListComponent
import com.ideaapp.shared.note.secure.SecureComponent


interface NoteComponent {
    val noteChild: Value<ChildStack<*, NoteChild>>

    fun openCreteNote()

//    data class Children(
//        val listChild: Child.Created<*, NoteListComponent>,
//        val createEditChild: Child.Created<*, NoteCreateEditComponent>?,
//        val secureListChild: Child.Created<*, SecureComponent>
//    )


    sealed interface NoteChild {
        class ListChild(val component: NoteListComponent) : NoteChild
        class CreateEditChild(val component: NoteCreateEditComponent) : NoteChild
        class SecureChild(val component: SecureComponent) : NoteChild
    }
}