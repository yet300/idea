package com.ideaapp.presentation.screens.task.main


import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.note.main.components.LargeFAB
import com.ideaapp.presentation.screens.task.main.components.EmptyScreen
import com.ideaapp.presentation.screens.task.main.components.TaskItem
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navController: NavHostController,
    listState: LazyListState,
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {

    val tasks by viewModel.state.collectAsState()

    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    LaunchedEffect(tasks.taskList) {
        Log.d("TaskScreen", "tasks.taskList changed: $tasks")

    }



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
            if (tasks.taskList.isNotEmpty()) {
                LazyColumn(
                    state = listState,
                    contentPadding = contentPadding,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    items(tasks.taskList, key = { task -> task.id }) { task ->
                        TaskItem(
                            task = task,
                            onTaskCheckedChange = viewModel::updateTaskComplete,
                        )
                    }
                    // Добавляем пустые элементы в конец списка
                    repeat(10) {
                        item {
                            Spacer(modifier = Modifier.height(20.dp)) // Выберите высоту, которая вам подходит
                        }
                    }
                }
            } else {
                EmptyScreen()
            }
        }
    )
}

