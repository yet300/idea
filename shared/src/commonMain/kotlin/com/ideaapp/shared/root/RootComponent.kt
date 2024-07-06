package com.ideaapp.shared.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.ideaapp.shared.note.create_edit.NoteCreateEditComponent
import com.ideaapp.shared.note.secure.SecureComponent
import com.ideaapp.shared.tabs.TabsComponent

interface RootComponent : BackHandlerOwner {
    val childStackNavigation: Value<ChildStack<*, NavChild>>

    fun onBackClicked()
    sealed class NavChild {
        class TabsChild(val component: TabsComponent) : NavChild()

        class NoteCreateEditChild(val component: NoteCreateEditComponent) : NavChild()

        class NoteSecureChild(val component: SecureComponent) : NavChild()
    }
}