package com.ideaapp.shared.tabs

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ideaapp.shared.note.main.NoteComponent
import com.ideaapp.shared.settings.SettingsComponent
import com.ideaapp.shared.task.main.TaskComponent

interface TabsComponent {

    val childStackNavigation: Value<ChildStack<*, NavChild>>

    fun openNote()
    fun openSettings()
    fun openTask()

    sealed class NavChild {
        class TaskChild(val component: TaskComponent) : NavChild()

        class SettingsChild(val component: SettingsComponent) : NavChild()

        class NoteChild(val component: NoteComponent) : NavChild()
    }
}