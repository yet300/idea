package com.ideaapp.shared.compose.ui.navigation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import com.ideaapp.shared.compose.ui.components.FAB
import com.ideaapp.shared.compose.ui.navigation.components.NavBar
import com.ideaapp.shared.compose.ui.navigation.components.Screens
import com.ideaapp.shared.compose.ui.screens.note.create_edit.NoteCreateEditScreen
import com.ideaapp.shared.compose.ui.screens.note.secure.NoteSecureScreen
import com.ideaapp.shared.compose.ui.screens.settings.SettingsScreen
import com.ideaapp.shared.compose.ui.screens.task.create_edit.TaskDetailScreen
import com.ideaapp.shared.compose.ui.screens.task.main.TaskScreen


@Composable
fun SetupNavHost(
    context: Context,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val floatingBottomRoutes = setOf(Screens.Note.rout, Screens.Task.rout)
    val showFloatingBottom = currentRoute in floatingBottomRoutes

    Scaffold(
        bottomBar = {
            NavBar(
                navController = navController,
                currentRoute = currentRoute
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showFloatingBottom,
            ) {
                FAB(
                    onClick = {
                        if (currentRoute == Screens.Note.rout) navController.navigate(
                            Screens.NoteCreateEdit.rout
                        ) else navController.navigate(
                            Screens.TaskCreateEdit.rout
                        )
                    },
                )
            }
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Screens.Note.rout,
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
                composable(route = Screens.Note.rout) {
                    NoteScreen(
                        navController = navController,
                        activity = context as AppCompatActivity,
                    )
                }

                composable(
                    route = Screens.Secure.rout
                ) {
                    NoteSecureScreen(navController = navController)
                }

                composable(
                    route = Screens.NoteCreateEdit.rout + "?noteId={noteId}",
                    arguments = listOf(
                        navArgument(
                            name = "noteId"
                        ) {
                            type = NavType.LongType
                            defaultValue = -1L
                        }
                    )
                ) {
                    NoteCreateEditScreen(
                        navController = navController,
                        context = context
                    )
                }

                composable(
                    route = Screens.Settings.rout
                ) {
                    SettingsScreen(
                        context = context
                    )
                }


                composable(
                    route = Screens.Task.rout,
                ) {
                    TaskScreen(navController = navController)
                }

                composable(
                    route = Screens.TaskCreateEdit.rout + "?taskId={taskId}",
                    arguments = listOf(
                        navArgument(
                            name = "taskId"
                        ) {
                            type = NavType.LongType
                            defaultValue = -1L
                        }
                    )
                ) {
                    TaskDetailScreen(navController = navController)
                }

            }
        }
    )
}

