package com.ideaapp.presentation.screens.task.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.note.main.components.LargeFAB
import com.ideaapp.presentation.screens.task.main.components.EmptyScreen
import com.ideaapp.presentation.screens.task.main.components.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navController: NavHostController,
    listState: LazyListState,
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {

    val tasks = viewModel.tasks.observeAsState(listOf()).value


    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.tasks),
                        style = MaterialTheme.typography.headlineSmall,
                    )

                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            LargeFAB(onClick = {
                navController.navigate(Screens.CreateTask.rout)
            })
        },
        content = { contentPadding ->
            Box(
                modifier = modifier
                    .padding(contentPadding)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                if (tasks.isNotEmpty()) {
                    LazyColumn(
                        state = listState,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        items(tasks, key = { task -> task.id }) { task ->
                            TaskItem(
                                taskName = task.name,
                                isCompleted = task.isCompleted,
                                description = task.description ?: ""
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

