package com.ideaapp.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.ideaapp.domain.usecase.note.CreateNoteUseCase
import com.ideaapp.domain.usecase.note.DeleteNoteUseCase
import com.ideaapp.domain.usecase.note.GetNoteByIdUseCase
import com.ideaapp.shared.note.create_edit.DefaultNoteCreateEditComponent
import com.ideaapp.shared.tabs.DefaultTabsComponent
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, KoinComponent, ComponentContext by componentContext {

    private val createNoteUseCase by inject<CreateNoteUseCase>()
    private val getNoteByIdUseCase by inject<GetNoteByIdUseCase>()
    private val deleteNoteUseCase by inject<DeleteNoteUseCase>()

    private val navigationStack = StackNavigation<Config>()

    private val _childStackNavigation = childStack(
        source = navigationStack,
        serializer = Config.serializer(),
        initialConfiguration = Config.Tabs,
        handleBackButton = true,
        childFactory = ::createChildNavigation,
    )

    private fun createChildNavigation(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.NavChild = when (config) {

        Config.Tabs -> RootComponent.NavChild.TabsChild(
            component = DefaultTabsComponent(
                componentContext = componentContext,
                onOpenCreateEditClick = { navigationStack.pushNew(Config.NoteCreateEdit()) }
            )
        )

        Config.Secure -> TODO()

        is Config.NoteCreateEdit -> RootComponent.NavChild.NoteCreateEditChild(
            component = DefaultNoteCreateEditComponent(
                createNoteUseCase = createNoteUseCase,
                getNoteByIdUseCase = getNoteByIdUseCase,
                deleteNoteUseCase = deleteNoteUseCase,
                onBack = { onBackClicked() },
                stateKeeper = stateKeeper
            )
        )
    }

    override val childStackNavigation: Value<ChildStack<*, RootComponent.NavChild>> =
        _childStackNavigation

    override fun onBackClicked() {
        navigationStack.pop()
    }


    @Serializable
    private sealed class Config {
        @Serializable
        data object Tabs : Config()

        @Serializable
        data class NoteCreateEdit(val id: Long? = 0L) : Config()

        @Serializable
        data object Secure : Config()

    }
}