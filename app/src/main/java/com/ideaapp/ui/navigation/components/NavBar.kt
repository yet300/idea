package com.ideaapp.ui.navigation.components

import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ideaapp.R
import com.ideaapp.shared.compose.ui.navigation.components.NavController.Companion.navigateToMain
import com.ideaapp.shared.compose.ui.navigation.components.Screens


@Composable
fun NavBar(
    navController: NavController,
    currentRoute: String?,
) {

    val items = listOf(
        com.ideaapp.shared.compose.ui.navigation.components.BottomNavigationItem(
            selectedIcon = Icons.AutoMirrored.Filled.Note,
            unselectedIcon = Icons.AutoMirrored.Outlined.Note,
            label = R.string.Note,
            isSelected = currentRoute == Screens.Note.rout,
            onClick = {
                navController.navigateToMain(destination = Screens.Note.rout)
            }
        ),
        com.ideaapp.shared.compose.ui.navigation.components.BottomNavigationItem(
            selectedIcon = Icons.Default.Task,
            unselectedIcon = Icons.Outlined.Task,
            label = R.string.Task,
            isSelected = currentRoute == Screens.Task.rout,
            onClick = {
                navController.navigateToMain(destination = Screens.Task.rout)
            }
        ),
        com.ideaapp.shared.compose.ui.navigation.components.BottomNavigationItem(
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            label = R.string.settings,
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
                                text = stringResource(id = item.label),
                                style = MaterialTheme.typography.titleSmall
                            )
                        },
                        onClick = { item.onClick(index) },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = stringResource(id = item.label)
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
    @StringRes val label: Int,
    val isSelected: Boolean,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val onClick: (Int) -> Unit,
)


