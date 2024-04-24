package com.ideaapp.ui.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavBar(
    navController: NavController,
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val bottomBarRoutes = setOf(Screens.Home.rout, Screens.Task.rout)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar = navBackStackEntry?.destination?.route in bottomBarRoutes
    if (showBottomBar) {
        NavigationBar(
            content = {
                items.forEachIndexed { index, item ->
                    val isSelected = selectedItemIndex == index
                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(
                                text = item.label,
                                style = MaterialTheme.typography.titleSmall
                            )
                        },
                        onClick = { onItemSelected(index) },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.route
                            )
                        },
                        modifier = Modifier
                    )
                }
            },
        )
    }
}
