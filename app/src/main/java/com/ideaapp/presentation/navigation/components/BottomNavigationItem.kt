package com.ideaapp.presentation.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavigationItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val items = listOf(
    BottomNavigationItem(
        route = Screens.Home.rout,
        selectedIcon = Icons.Filled.Menu,
        unselectedIcon = Icons.Outlined.Menu
    ),
    BottomNavigationItem(
        route = Screens.Search.rout,
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search

    ),
    BottomNavigationItem(
        route = Screens.Create.rout,
        selectedIcon = Icons.Filled.Create,
        unselectedIcon = Icons.Outlined.Create
    )
)
