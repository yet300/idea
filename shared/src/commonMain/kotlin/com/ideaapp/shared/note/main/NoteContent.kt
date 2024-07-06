package com.ideaapp.shared.note.main


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
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
                onClick = { component.openCreteEdit }
            )
        },
        content = { padding ->
            Children(
                stack = component.noteChild,
                modifier = Modifier.fillMaxSize(),
                animation = stackAnimation(fade()),
            ) {
                when (val child = it.instance) {
                    is NoteComponent.NoteChild.ListChild -> NoteListContent(
                        component = child.component,
                        paddingValues = padding
                    )
                }
            }
        }
    )
}