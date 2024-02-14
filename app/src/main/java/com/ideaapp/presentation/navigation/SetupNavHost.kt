package com.ideaapp.presentation.navigation


import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ideaapp.presentation.screens.note.create.CreateScreen
import com.ideaapp.presentation.screens.note.main.MainScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.BottomNavigationItem
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.note.details.DetailsScreen
import com.ideaapp.presentation.screens.note.secure.SecureScreen
import com.ideaapp.presentation.screens.settings.SettingsScreen
import com.ideaapp.presentation.screens.task.create.CreateTaskScreen
import com.ideaapp.presentation.screens.task.main.TaskScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetupNavHost(
    navController: NavHostController,
) {
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val items = listOf(
        BottomNavigationItem(
            label = stringResource(R.string.Note),
            route = Screens.Home.rout,
            selectedIcon = Icons.AutoMirrored.Filled.Note,
            unselectedIcon = Icons.AutoMirrored.Outlined.Note
        ),
        BottomNavigationItem(
            label = stringResource(id = R.string.Task),
            route = Screens.Task.rout,
            selectedIcon = Icons.Filled.Task,
            unselectedIcon = Icons.Outlined.Task
        )
    )


    showBottomBar = when (navBackStackEntry?.destination?.route) {
        Screens.Details.rout + "/{id}" -> false // on this screen bottom bar should be hidden
        Screens.Create.rout -> false
        Screens.Secure.rout -> false
        Screens.Settings.rout -> false
        else -> true // in all other cases show bottom bar
    }

    val context = LocalContext.current


    Scaffold(
        bottomBar = {
            var selectedItemIndex by rememberSaveable {
                mutableIntStateOf(0)
            }
            if (showBottomBar)
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        val isSelected =
                            selectedItemIndex == index && navBackStackEntry?.destination?.route == item.route
                        NavigationBarItem(
                            selected = isSelected,
                            label = {
                                Text(text = item.label)
                            },
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    },
                                    contentDescription = item.route
                                )
                            }
                        )

                    }

                }
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Screens.Home.rout,
                enterTransition = {
                    fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                            scaleIn(
                                initialScale = 0.92f,
                                animationSpec = tween(220, delayMillis = 90)
                            )
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(90))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                            scaleIn(
                                initialScale = 0.92f,
                                animationSpec = tween(220, delayMillis = 90)
                            )
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(90))
                },
            ) {
                composable(route = Screens.Home.rout) {
                    MainScreen(navController = navController, hiltViewModel())

                }

                composable(
                    route = Screens.Secure.rout
                ) {
                    SecureScreen(navController = navController, hiltViewModel())
                }

                composable(
                    route = Screens.Details.rout + "/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) {
                    DetailsScreen(
                        navController = navController,
                        it.arguments?.getString("id"),
                        hiltViewModel()
                    )
                }

                composable(
                    route = Screens.Settings.rout
                ) {
                    SettingsScreen(
                        navController = navController,
                        context = context
                    )
                }


                composable(
                    route = Screens.Create.rout
                ) {

                    CreateScreen(navController = navController, context, hiltViewModel())

                }

                composable(
                    route = Screens.Task.rout,
                ) {
                    TaskScreen(navController, hiltViewModel())
                }

                dialog(
                    route = Screens.CreateTask.rout,

                    ) {
                    CreateTaskScreen(
                        onDismissRequest = { navController.navigate(Screens.Task.rout) },
                        context,
                        hiltViewModel()
                    )
                }
            }
        }
    )
}



