package com.ideaapp.shared.compose.ui.navigation.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.ideaapp.shared.compose.ui.navigation.components.NavController.Companion.navigateToMain
import ideasapp.shared.generated.resources.Note
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.Task
import ideasapp.shared.generated.resources.settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun NavBar(
    navController: NavController,
    currentRoute: String?,
) {

    val items = listOf(
        BottomNavigationItem(
            selectedIcon = Icons.AutoMirrored.Filled.Note,
            unselectedIcon = Icons.AutoMirrored.Outlined.Note,
            label = Res.string.Note,
            isSelected = currentRoute == Screens.Note.rout,
            onClick = {
                navController.navigateToMain(destination = Screens.Note.rout)
            }
        ),
        BottomNavigationItem(
            selectedIcon = Icons.Default.Task,
            unselectedIcon = Icons.Outlined.Task,
            label = Res.string.Task,
            isSelected = currentRoute == Screens.Task.rout,
            onClick = {
                navController.navigateToMain(destination = Screens.Task.rout)
            }
        ),
        BottomNavigationItem(
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            label = Res.string.settings,
            isSelected = currentRoute == Screens.Settings.rout,
            onClick = {
                navController.navigateToMain(destination = Screens.Settings.rout)
            }
        )
    )

    val bottomBarRoutes = setOf(Screens.Note.rout, Screens.Task.rout, Screens.Settings.rout)
    val showBottomBar = currentRoute in bottomBarRoutes
    if (showBottomBar) {
        NavigationBar(
            content = {
                items.forEachIndexed { index, item ->
                    val isSelected = item.isSelected
                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(
                                text = stringResource(item.label),
                                style = MaterialTheme.typography.titleSmall
                            )
                        },
                        onClick = { item.onClick(index) },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = stringResource(item.label)
                            )
                        },
                        modifier = Modifier
                    )
                }
            }
        )
    }
}

data class BottomNavigationItem(
    val label: StringResource,
    val isSelected: Boolean,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val onClick: (Int) -> Unit,
)


