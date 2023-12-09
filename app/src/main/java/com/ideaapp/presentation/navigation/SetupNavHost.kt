package com.ideaapp.presentation.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ideaapp.presentation.screens.search.SearchScreen
import com.ideaapp.presentation.screens.create.CreateScreen
import com.ideaapp.presentation.screens.details.DetailsScreen
import com.ideaapp.presentation.screens.main.MainScreen
import com.ideaapp.presentation.ui.theme.components.Screens
import com.ideaapp.presentation.ui.theme.components.items


@Composable
fun SetupNavHost(navController: NavHostController) {


    Scaffold(
        bottomBar = {
            var selectedItemIndex by rememberSaveable {
                mutableStateOf(0)
            }
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.route
                            )
                        })

                }
            }
        },
        content = { paddingValue ->
            NavHost(
                navController = navController,
                startDestination = Screens.Home.rout
            ) {
                composable(route = Screens.Home.rout) {
                    MainScreen(navController = navController)

                }
                composable(
                    route = Screens.Details.rout + "/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) {
                    DetailsScreen(navController = navController, it.arguments?.getString("id"))
                }
                composable(route = Screens.Create.rout) {
                    CreateScreen(navController = navController)

                }

                composable(route = Screens.Search.rout) {
                    SearchScreen(navController = navController)
                }
            }
        }
    )

}


