package com.ideaapp.presentation.screens.main


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.details.DetailsViewModel
import com.ideaapp.presentation.screens.main.components.LargeFAB
import com.ideaapp.presentation.screens.main.components.Search
import com.ideaapp.presentation.ui.theme.components.NoteItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {

    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)


    val viewModel = hiltViewModel<MainViewModel>()

    val viewModelDetails = hiltViewModel<DetailsViewModel>()

    val notes = viewModel.notes.observeAsState(listOf()).value

    val searchText = remember {
        mutableStateOf("")
    }

    val queryNotes = if (searchText.value.isEmpty()) {
        notes
    } else {
        notes.filter {
            it.title.lowercase().contains(searchText.value.lowercase())
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                modifier = modifier,
                title = {
                    Search(query = searchText)
                },
                scrollBehavior = scrollBehavior,
            )

        },
        floatingActionButton = {
            LargeFAB(onClick = { navController.navigate(Screens.Create.rout) }, listState)
        },
        content = { contentPadding ->
            Box(modifier = modifier.padding(contentPadding)) {

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    state = listState
                ) {
//                    items(notes) { note ->
//                        NoteItem(
//                            title = note.title,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 5.dp, horizontal = 16.dp)
//                                .clickable { navController.navigate(Screens.Details.rout + "/${note.id}") }
//                        )
//                    }

                    items(queryNotes, key = { note -> note.id }) { note ->
                        note.let {
                            val dismissState = rememberDismissState()
                            val dismissDirection = dismissState.dismissDirection
                            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

                            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                                val scope = rememberCoroutineScope()
                                scope.launch {
                                    delay(500)
                                    viewModelDetails.deleteNote {

                                    }
                                }
                            }

                            val degrees by animateFloatAsState(
                                targetValue = if (dismissState.targetValue == DismissValue.Default) 0f
                                else -45f, label = ""
                            )

                            var itemAppeared by remember {
                                mutableStateOf(false)
                            }

                            LaunchedEffect(key1 = true) {
                                itemAppeared = true
                            }

                            AnimatedVisibility(
                                visible = itemAppeared && !isDismissed,
                                enter = expandVertically(
                                    animationSpec = tween(durationMillis = 500)
                                ),
                                exit = shrinkVertically(
                                    animationSpec = tween(durationMillis = 500)
                                )
                            ) {
                                SwipeToDismiss(
                                    state = dismissState,
                                    directions = setOf(DismissDirection.EndToStart),
                                    background = { SwipeItemBackground(degrees = degrees) },
                                    dismissContent = {
                                        NoteItem(
                                            title = note.title,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 5.dp, horizontal = 16.dp)
                                                .clickable { navController.navigate(Screens.Details.rout + "/${note.id}") }
                                        )
                                    })
                            }


                        }


                    }
                }
            }
        },
    )
}

@Composable
fun SwipeItemBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 200.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.error), contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier
                .rotate(degrees = degrees)
                .padding(end = 16.dp),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete),
        )
    }
}

