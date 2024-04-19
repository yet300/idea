package com.ideaapp.ui.screens.task.main


import android.content.Context
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.ideaapp.R
import com.ideaapp.ui.components.FAB
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ideaapp.ui.screens.task.create.CreateTaskModal
import com.ideaapp.ui.screens.task.main.components.TaskContent
import com.ideasapp.domain.model.Reminder
import com.ideasapp.domain.model.Task


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    context: Context,
    viewModel: TaskViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val tasks by viewModel.tasks.collectAsStateWithLifecycle(initialValue = emptyList())

    val snackBarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var showBottomSheet by remember { mutableStateOf(false) }


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
            )
        },
        floatingActionButton = {
            FAB(
                onClick = {
                    showBottomSheet = true
                },
                modifier = modifier.safeDrawingPadding()
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        content = {
            CreateTaskModal(
                showBottomSheet = showBottomSheet,
                onDismiss = { showBottomSheet = false },
                onCreateTask = { name, description, onTaskCreated ->
                    val task = Task(
                        name = name,
                        description = description,
                        reminderTime = onTaskCreated
                    )
                    viewModel.createTask(
                        task
                    ) {
                        showBottomSheet = false
                    }
                    if (onTaskCreated != 0L) {
                        viewModel.createReminderTask(
                            Reminder(
                                itemId = task.id,
                                name = name,
                                description = description,
                                reminderTime = onTaskCreated
                            )
                        )
                    }
                },
                context = context
            )
            TaskContent(
                viewModel = viewModel,
                navController = navController,
                contentPadding = it,
                tasks = tasks,
                snackBarHostState = snackBarHostState,
            )

        }
    )
}

