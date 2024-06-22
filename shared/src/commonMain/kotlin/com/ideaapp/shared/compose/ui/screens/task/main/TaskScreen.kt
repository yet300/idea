package com.ideaapp.shared.compose.ui.screens.task.main


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ideaapp.shared.compose.ui.screens.task.main.components.TaskContent
import com.ideaapp.shared.compose.ui.navigation.components.NavController
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.tasks
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = koinViewModel(),
    navController: NavController,
) {
    val tasks by viewModel.tasks.collectAsStateWithLifecycle(initialValue = emptyList())

    val snackBarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = modifier.padding(vertical = 250.dp)
            )
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.tasks),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
        content = {
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

