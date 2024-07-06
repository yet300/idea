package com.ideaapp.shared.tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ideaapp.shared.note.main.DefaultNoteComponent
import com.ideaapp.shared.settings.DefaultSettingsComponent
import com.ideaapp.shared.task.main.DefaultTaskComponent
import kotlinx.serialization.Serializable

class DefaultTabsComponent(
    componentContext: ComponentContext,
    private val onOpenCreateEditClick: (Long?) -> Unit,
) : TabsComponent, ComponentContext by componentContext {

    private val navigationStack = StackNavigation<Config>()

    private val _childStackNavigation = childStack(
        source = navigationStack,
        serializer = Config.serializer(),
        initialConfiguration = Config.Note,
        handleBackButton = true,
        childFactory = ::createChildNavigation,
    )

    private fun createChildNavigation(
        config: Config,
        componentContext: ComponentContext,
    ): TabsComponent.NavChild = when (config) {
        is Config.Note -> TabsComponent.NavChild.NoteChild(
            component = DefaultNoteComponent(
                componentContext = componentContext,
                openCreteEdit = onOpenCreateEditClick
            )
        )

        is Config.Task -> TabsComponent.NavChild.TaskChild(
            component = DefaultTaskComponent(componentContext)
        )


        is Config.Settings -> TabsComponent.NavChild.SettingsChild(
            component = DefaultSettingsComponent(componentContext)
        )
    }

    override val childStackNavigation: Value<ChildStack<*, TabsComponent.NavChild>> =
        _childStackNavigation


    override fun openNote() {
        navigationStack.bringToFront(Config.Note)
    }

    override fun openTask() {
        navigationStack.bringToFront(Config.Task)
    }

    override fun openSettings() {
        navigationStack.bringToFront(Config.Settings)
    }

    @Serializable
    private sealed class Config {
        @Serializable
        data object Task : Config()

        @Serializable
        data object Settings : Config()


        @Serializable
        data object Note : Config()
    }
}