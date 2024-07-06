package com.ideaapp.shared.note.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ideaapp.domain.usecase.note.GetNoteUseCase
import com.ideaapp.shared.note.list.DefaultNoteListComponent
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultNoteComponent(
    componentContext: ComponentContext,
    override val openCreteEdit: (Long?) -> Unit,
) : NoteComponent, KoinComponent, ComponentContext by componentContext {
    private val getNoteUseCase by inject<GetNoteUseCase>()
    private val navigation = StackNavigation<Config>()

    private val _childStackNavigation = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.List,
        handleBackButton = true,
        childFactory = ::child,
    )

    override val noteChild: Value<ChildStack<*, NoteComponent.NoteChild>> = _childStackNavigation


    private fun child(config: Config, componentContext: ComponentContext): NoteComponent.NoteChild =
        when (config) {
            Config.List -> NoteComponent.NoteChild.ListChild(
                DefaultNoteListComponent(
                    getNoteUseCase = getNoteUseCase,
                    noteClicked = openCreteEdit,
                )
            )

        }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object List : Config

    }
}