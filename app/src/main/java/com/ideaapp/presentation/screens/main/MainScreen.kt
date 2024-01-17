package com.ideaapp.presentation.screens.main


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.main.components.EmptyScreen
import com.ideaapp.presentation.screens.main.components.LargeFAB
import com.ideaapp.presentation.screens.main.components.Search
import com.ideaapp.presentation.ui.theme.components.NoteItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    listState: LazyGridState,
    modifier: Modifier = Modifier
) {

    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)


    val viewModel = hiltViewModel<MainViewModel>()


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
            Box(
                modifier = modifier
                    .padding(contentPadding)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                if (notes.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        columns = GridCells.Fixed(2),
                        state = listState
                    ) {
                        items(queryNotes, key = { note -> note.id }) { note ->
                            NoteItem(
                                title = note.title,
                                image = note.imageUri,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp, horizontal = 5.dp)
                                    .clickable { navController.navigate(Screens.Details.rout + "/${note.id}") }
                            )
                        }
                    }
                } else {
                    EmptyScreen()
                }
            }
        },
    )
}

