package com.ideaapp.presentation.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material.icons.outlined.Task
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavigationItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val items = listOf(
    BottomNavigationItem(
        route = Screens.Home.rout,
        selectedIcon = Icons.Filled.Notes,
        unselectedIcon = Icons.Outlined.Notes
    ),
    BottomNavigationItem(
        route = Screens.Task.rout,
        selectedIcon = Icons.Filled.Task,
        unselectedIcon = Icons.Outlined.Task

    )
)
