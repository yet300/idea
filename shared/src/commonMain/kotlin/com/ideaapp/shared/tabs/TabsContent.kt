package com.ideaapp.shared.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.ideaapp.shared.compose.components.NavigationBar
import com.ideaapp.shared.note.main.NoteContent
import com.ideaapp.shared.settings.SettingsContent
import com.ideaapp.shared.task.main.TaskContent

@Composable
internal fun TabsContent(
    component: TabsComponent,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Children(
            stack = component.childStackNavigation,
            modifier = Modifier.weight(1F).consumeWindowInsets(WindowInsets.navigationBars),
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is TabsComponent.NavChild.NoteChild -> NoteContent(
                    component = child.component,
                    modifier = Modifier.fillMaxSize()
                )

                is TabsComponent.NavChild.SettingsChild -> SettingsContent(
                    component = child.component,
                    modifier = Modifier.fillMaxSize()
                )

                is TabsComponent.NavChild.TaskChild -> TaskContent(
                    component = child.component,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        NavigationBar(
            component = component,
            modifier = Modifier
        )
    }
}