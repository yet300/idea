package com.ideaapp.shared.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.ideaapp.shared.tabs.TabsComponent
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.note
import ideasapp.shared.generated.resources.settings
import ideasapp.shared.generated.resources.task
import org.jetbrains.compose.resources.stringResource


@Composable
fun NavigationBar(
    component: TabsComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.childStackNavigation.subscribeAsState()
    val activeComponent = stack.active.instance

    NavigationBar(
        content = {
            NavigationBarItem(
                selected = activeComponent is TabsComponent.NavChild.NoteChild,
                label = {
                    Text(
                        text = stringResource(Res.string.note),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                onClick = component::openNote,
                icon = {
                    Icon(
                        imageVector = if (activeComponent is TabsComponent.NavChild.NoteChild) Icons.AutoMirrored.Filled.Note else Icons.AutoMirrored.Outlined.Note,
                        contentDescription = stringResource(Res.string.note)
                    )
                },
            )

            NavigationBarItem(
                selected = activeComponent is TabsComponent.NavChild.TaskChild,
                label = {
                    Text(
                        text = stringResource(Res.string.task),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                onClick = component::openTask,
                icon = {
                    Icon(
                        imageVector = if (activeComponent is TabsComponent.NavChild.TaskChild) Icons.Default.Task else Icons.Outlined.Task,
                        contentDescription = stringResource(Res.string.task)
                    )
                },
            )

            NavigationBarItem(
                selected = activeComponent is TabsComponent.NavChild.SettingsChild,
                label = {
                    Text(
                        text = stringResource(Res.string.settings),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                onClick = component::openSettings,
                icon = {
                    Icon(
                        imageVector = if (activeComponent is TabsComponent.NavChild.SettingsChild) Icons.Default.Settings else Icons.Outlined.Settings,
                        contentDescription = stringResource(Res.string.settings)
                    )
                },
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
    )
}