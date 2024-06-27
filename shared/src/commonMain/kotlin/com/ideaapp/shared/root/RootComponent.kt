package com.ideaapp.shared.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ideaapp.shared.note.main.NoteComponent
import com.ideaapp.shared.settings.SettingsComponent
import com.ideaapp.shared.task.main.TaskComponent

interface RootComponent {
    val childStackNavigation: Value<ChildStack<*, NavChild>>

    fun openNote()
    fun openTask()
    fun openSettings()

    sealed class NavChild {
        class NoteChild(val component: NoteComponent) : NavChild()
        class TaskChild(val component: TaskComponent) : NavChild()
        class SettingsChild(val component: SettingsComponent) : NavChild()
    }
}