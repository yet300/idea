package com.ideaapp.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ideaapp.shared.note.main.DefaultNoteComponent
import com.ideaapp.shared.settings.DefaultSettingsComponent
import com.ideaapp.shared.task.main.DefaultTaskComponent
import  kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

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
    ): RootComponent.NavChild = when (config) {
        is Config.Note -> RootComponent.NavChild.NoteChild(
            component = DefaultNoteComponent(
                componentContext = componentContext,
            )
        )

        is Config.Task -> RootComponent.NavChild.TaskChild(
            component = DefaultTaskComponent(componentContext)
        )

        is Config.Settings -> RootComponent.NavChild.SettingsChild(
            component = DefaultSettingsComponent(componentContext)
        )
    }

    override val childStackNavigation: Value<ChildStack<*, RootComponent.NavChild>> =
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
        data object Note : Config()

        @Serializable
        data object Task : Config()

        @Serializable
        data object Settings : Config()
    }
}