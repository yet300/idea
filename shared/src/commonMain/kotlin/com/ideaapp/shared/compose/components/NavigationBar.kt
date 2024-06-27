package com.ideaapp.shared.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.ideaapp.shared.root.RootComponent
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.note
import ideasapp.shared.generated.resources.settings
import ideasapp.shared.generated.resources.task
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

data class BottomNavigationItem(
    val label: StringResource,
    val isSelected: Boolean,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val onClick: () -> Unit,
)

@Composable
fun NavigationBar(
    component: RootComponent,
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items by remember {
        mutableStateOf(
            listOf(
                BottomNavigationItem(
                    selectedIcon = Icons.AutoMirrored.Filled.Note,
                    unselectedIcon = Icons.AutoMirrored.Outlined.Note,
                    label = Res.string.note,
                    isSelected = false,
                    onClick = component::openNote
                ),
                BottomNavigationItem(
                    selectedIcon = Icons.Default.Task,
                    unselectedIcon = Icons.Outlined.Task,
                    label = Res.string.task,
                    isSelected = false,
                    onClick = component::openTask
                ),
                BottomNavigationItem(
                    selectedIcon = Icons.Default.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                    label = Res.string.settings,
                    isSelected = false,
                    onClick = component::openSettings
                )
            )
        )
    }

    androidx.compose.material3.NavigationBar(
        content = {
            items.forEachIndexed { index, item ->
                val selected = selectedItem == index
                NavigationBarItem(
                    selected = selected,
                    label = {
                        Text(
                            text = stringResource(item.label),
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    onClick = {
                        selectedItem = index
                        item.onClick()
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = stringResource(item.label)
                        )
                    },
                    modifier = Modifier
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}