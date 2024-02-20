package com.ideaapp.ui.screens.task.main


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.screens.note.main.components.LargeFAB
import com.ideaapp.ui.screens.task.main.components.EmptyScreen
import com.ideaapp.ui.screens.task.main.components.TaskItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ideaapp.ui.components.SnackBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navController: NavHostController,
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {

    val tasks by viewModel.tasks.collectAsStateWithLifecycle(initialValue = emptyList())

    var completedItemsShown by rememberSaveable { mutableStateOf(false) }


    // Store a mutable version of the list locally so it is updated quickly while dragging.
    val uncompletedTaskItems by rememberUpdatedState(tasks.filter { !it.isComplete }
        .toMutableStateList())
    val completedTaskItems = remember(tasks) { tasks.filter { it.isComplete } }

    val scope = rememberCoroutineScope()
    val snackbarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    val taskDelete = stringResource(id = R.string.task_delete)
    val undo = stringResource(id = R.string.undo)



    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.tasks),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                },
                scrollBehavior = scrollBehavior,
                modifier = modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            LargeFAB(
                onClick = { navController.navigate(Screens.CreateTask.rout) },
                modifier = modifier.safeDrawingPadding()
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { contentPadding ->
            if (tasks.isNotEmpty() || completedTaskItems.isNotEmpty() || uncompletedTaskItems.isNotEmpty()) {
                LazyColumn(
                    state = rememberLazyListState(),
                    contentPadding = contentPadding,
                    modifier = modifier
                        .fillMaxWidth()
                ) {

                    items(uncompletedTaskItems, key = { task -> task.id }) { task ->
                        TaskItem(
                            task = task,
                            onTaskCheckedChange = {
                                viewModel.updateTaskComplete(task.id, it)
                            }, onDelete = {
                                viewModel.deleteTask(task)
                                SnackBar(
                                    scope = scope,
                                    snackbarHostState = snackbarHostState,
                                    msg = taskDelete,
                                    actionLabel = undo,
                                    onAction = { viewModel.undoDeleteTask() })
                            }
                        )
                    }
                    item {
                        AnimatedVisibility(visible = completedTaskItems.isNotEmpty()) {
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                            ) {
                                TextButton(
                                    onClick = {
                                        completedItemsShown = !completedItemsShown
                                    },
                                    modifier = modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.complete),
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Icon(
                                        imageVector = if (completedItemsShown) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand/Collapse"
                                    )
                                }
                            }
                        }

                    }


                    items(completedTaskItems, key = { it.id }) { task ->
                        val isVisible = remember { MutableTransitionState(completedItemsShown) }
                        LaunchedEffect(completedItemsShown) {
                            isVisible.targetState = completedItemsShown
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
                                        snackbarHostState = snackbarHostState,
                                        msg = taskDelete,
                                        actionLabel = undo,
                                        onAction = { viewModel.undoDeleteTask() }
                                    )
                                }
                            )
                        }
                    }

                    // Добавляем пустые элементы в конец списка
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
    )
}


