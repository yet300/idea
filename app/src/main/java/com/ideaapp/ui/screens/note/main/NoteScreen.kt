package com.ideaapp.ui.screens.note.main


import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.components.FAB
import com.ideaapp.ui.screens.note.main.components.EmptyScreen
import com.ideaapp.ui.screens.note.main.components.NoteContent
import com.ideaapp.ui.screens.note.main.components.Search


@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel,
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()

    val searchText by viewModel.searchText.collectAsState()
    val queryNotes = if (searchText.isEmpty()) {
        notes
    } else {
        notes.filter {
            !it.isPrivate
            it.title.lowercase().contains(searchText.lowercase())
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        floatingActionButton = {
            FAB(
                onClick = { navController.navigate(Screens.NoteCreateEdit.rout) },
                modifier.safeDrawingPadding()
            )
        },
        topBar = {
            Search(
                navController = navController,
                query = searchText,
                activity = LocalContext.current as AppCompatActivity,
                onQueryChange = {
                    viewModel.setSearchText(it)
                },
                onSearchChange = {
                    viewModel.performSearch()
                },
                modifier = modifier
            )
        },
        content = {
            if (queryNotes.isNotEmpty()) {
                NoteContent(
                    navController = navController,
                    paddingValues = it,
                    queryNotes = queryNotes,
                )
            } else {
                EmptyScreen()
            }
        }
    )
}

