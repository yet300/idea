package com.ideaapp.ui.screens.task.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ideaapp.R
import com.ideaapp.ui.components.SnackBar
import com.ideaapp.ui.components.TextIconComponent
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.screens.task.main.TaskViewModel
import com.ideasapp.domain.model.Task


@Composable
fun TaskScreenContent(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel,
    navController: NavController,
    contentPadding: PaddingValues,
    tasks: List<Task>,
    snackBarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    val taskDelete = stringResource(id = R.string.task_delete)
    val undo = stringResource(id = R.string.undo)
    var completedTaskShow by rememberSaveable { mutableStateOf(false) }
    // Store a mutable version of the list locally so it is updated quickly while dragging.
    val uncompletedTasks by rememberUpdatedState(tasks.filter { !it.isComplete }
        .toMutableStateList())
    val completedTasks = remember(tasks) { tasks.filter { it.isComplete } }

    if (tasks.isNotEmpty() || completedTasks.isNotEmpty() || uncompletedTasks.isNotEmpty()) {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = contentPadding,
            modifier = modifier
                .fillMaxWidth()
        ) {

            items(uncompletedTasks, key = { task -> task.id }) { task ->
                TaskItem(
                    task = task,
                    onTaskCheckedChange = {
                        viewModel.updateTaskComplete(task.id, it)
                    }, onDelete = {
                        viewModel.deleteTask(task)
                        SnackBar(
                            scope = scope,
                            snackbarHostState = snackBarHostState,
                            msg = taskDelete,
                            actionLabel = undo,
                            onAction = { viewModel.undoDeleteTask() })
                    },
                    modifier = modifier.clickable {
                        navController.navigate(
                            Screens.TaskDetail.rout + "?taskId=${task.id}"
                        )
                    }
                )
            }
            item {
                AnimatedVisibility(visible = completedTasks.isNotEmpty()) {
                    TextIconComponent(
                        onClick = {
                            completedTaskShow = !completedTaskShow
                        },
                        text = stringResource(id = R.string.complete),
                        icon = if (completedTaskShow) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    )
                }
            }

            items(completedTasks, key = { it.id }) { task ->
                val isVisible = remember { MutableTransitionState(completedTaskShow) }
                LaunchedEffect(completedTaskShow) {
                    isVisible.targetState = completedTaskShow
                }
                AnimatedVisibility(
                    visibleState = isVisible,
                    enter = slideInVertically(initialOffsetY = { -40 }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { -40 }) + fadeOut()
                ) {
                    TaskItem(
                        task = task,
                        onTaskCheckedChange = {
                            viewModel.updateTaskComplete(task.id, it)
                        }, onDelete = {
                            viewModel.deleteTask(task)
                            SnackBar(
                                scope = scope,
                                snackbarHostState = snackBarHostState,
                                msg = taskDelete,
                                actionLabel = undo,
                                onAction = { viewModel.undoDeleteTask() }
                            )
                        },
                        modifier = modifier.clickable {
                            navController.navigate(
                                Screens.TaskDetail.rout + "?taskId=${task.id}"
                            )
                        }
                    )
                }
            }

            repeat(10) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    } else {
        EmptyScreen()
    }
}