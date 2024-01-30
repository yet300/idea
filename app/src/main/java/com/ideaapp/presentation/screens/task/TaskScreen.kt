package com.ideaapp.presentation.screens.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.task.components.EmptyScreen
import com.ideaapp.presentation.screens.task.components.TaskItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())


    val viewModel = hiltViewModel<TaskViewModel>()
    val tasks = viewModel.tasks.observeAsState(listOf()).value



    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = {
                    Text(
                        "Tasks",
                        maxLines = 1,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                },

                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    navController.navigate(Screens.CreateTask.rout)
                },
                modifier = modifier.padding(vertical = 80.dp)
            ) {
                Icon(
                    Icons.Filled.Create,
                    stringResource(id = R.string.create),
                    modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
                )
            }
        },
        content = { contentPadding ->
            Box(
                modifier = modifier
                    .padding(contentPadding)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                if (tasks.isNotEmpty()) {
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize(),
                    ) {
                        items(tasks, key = { task -> task.id }) { task ->
                            TaskItem(
                                taskName = task.name,
                                isCompleted = task.isCompleted
                            )
                        }
                    }
                } else {
                    EmptyScreen()
                }
            }
        }
    )
}

