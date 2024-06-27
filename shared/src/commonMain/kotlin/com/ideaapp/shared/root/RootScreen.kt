package com.ideaapp.shared.root


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.ideaapp.shared.compose.components.NavigationBar
import com.ideaapp.shared.note.main.NoteContent
import com.ideaapp.shared.settings.SettingsContent
import com.ideaapp.shared.task.main.TaskContent


@Composable
internal fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                component = component
            )
        },
        content = {
            Column(
                modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Children(
                    stack = component.childStackNavigation,
                    modifier = modifier,
                    animation = stackAnimation(fade() + scale())
                ) {
                    when (val child = it.instance) {
                        is RootComponent.NavChild.NoteChild -> NoteContent(component = child.component)
                        is RootComponent.NavChild.SettingsChild -> SettingsContent(component = child.component)
                        is RootComponent.NavChild.TaskChild -> TaskContent(component = child.component)
                    }
                }
            }
        }
    )
}