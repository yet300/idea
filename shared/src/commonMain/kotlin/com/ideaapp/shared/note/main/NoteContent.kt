package com.ideaapp.shared.note.main


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ideaapp.shared.compose.components.FAB
import com.ideaapp.shared.note.list.NoteListContent

@Composable
fun NoteContent(
    component: NoteComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(
//        topBar ={
//            SearchBar(
//                isVisible = isSearchBarVisible.value,
//                activity = activity,
//                navController = navController,
//                onChange = {
//                    viewModel.setSearchText(it)
//                },
//                onSearchChange = {
//                    viewModel.performSearch()
//                },
//                value = searchText
//            )
//        },
        floatingActionButton = {
            FAB(
                onClick = { component.openCreteNote() }
            )
        },
        content = {
            NoteListContent(
                component = component.noteChild.value.,
                paddingValues = it
            )
        }
    )
}