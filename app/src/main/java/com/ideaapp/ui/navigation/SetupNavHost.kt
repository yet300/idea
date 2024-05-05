package com.ideaapp.ui.navigation


import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ideaapp.R
import com.ideaapp.ui.navigation.NavController.Companion.navigateToMain
import com.ideaapp.ui.navigation.components.CustomBottomNavigationItem
import com.ideaapp.ui.navigation.components.NavBar
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.screens.note.create_edit.NoteCreateEditScreen
import com.ideaapp.ui.screens.note.main.NoteScreen
import com.ideaapp.ui.screens.note.secure.NoteSecureScreen
import com.ideaapp.ui.screens.settings.SettingsScreen
import com.ideaapp.ui.screens.task.detail.TaskDetailScreen
import com.ideaapp.ui.screens.task.main.TaskScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetupNavHost(
    context: Context
) {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        CustomBottomNavigationItem(
            icon = Icons.AutoMirrored.Filled.Note,
            description = R.string.Note,
            isSelected = currentRoute == Screens.Note.rout,
            onClick = {
                navController.navigateToMain(destination = Screens.Note.rout)

            }
        ),
        CustomBottomNavigationItem(
            icon = Icons.Default.Task,
            description = R.string.Task,
            isSelected = currentRoute == Screens.Task.rout,
            onClick = {
                navController.navigateToMain(destination = Screens.Task.rout)
            }
        )
    )

    Scaffold(
        bottomBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
                    .padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.zIndex(2f)) {
                    NavBar(
                        navController = navController,
                        items = items,
                    )
                }
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
                        hiltViewModel(),
                        context as AppCompatActivity,
                    )
                }

                composable(
                    route = Screens.Secure.rout
                ) {
                    NoteSecureScreen(navController = navController, hiltViewModel())
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
                        viewModel = hiltViewModel(),
                        context = context
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
                    route = Screens.Task.rout,
                ) {
                    TaskScreen(context, hiltViewModel(), navController)
                }

                composable(
                    route = Screens.TaskDetail.rout + "?taskId={taskId}",
                    arguments = listOf(
                        navArgument(
                            name = "taskId"
                        ) {
                            type = NavType.LongType
                            defaultValue = -1L
                        }
                    )
                ) {
                    TaskDetailScreen(viewModel = hiltViewModel(), navController = navController)
                }
            }
        }
    )
}

