package com.ideaapp.ui.screens.note.main


import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ideaapp.ui.components.SearchBar
import com.ideaapp.ui.components.scrollConnectionToProvideVisibility
import com.ideaapp.ui.screens.note.main.components.EmptyScreen
import com.ideaapp.ui.screens.note.main.components.NoteContent


@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel,
    activity: AppCompatActivity,
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()

    val searchText by viewModel.searchText.collectAsState()
    val queryNotes = if (searchText.isEmpty()) {
        notes.filter {
            !it.isPrivate
        }
    } else {
        notes.filter {
            !it.isPrivate
            it.title.lowercase().contains(searchText.lowercase())
        }
    }

    val isSearchBarVisible = remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier
            .scrollConnectionToProvideVisibility(visible = isSearchBarVisible)
            .fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            SearchBar(
                isVisible = isSearchBarVisible.value,
                activity = activity,
                navController = navController,
                onChange = {
                    viewModel.setSearchText(it)
                },
                onSearchChange = {
                    viewModel.performSearch()
                },
                value = searchText
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