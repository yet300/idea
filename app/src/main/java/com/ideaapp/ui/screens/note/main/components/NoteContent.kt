package com.ideaapp.ui.screens.note.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ideaapp.ui.navigation.components.Screens
import com.ideasapp.domain.model.Note


@Composable
fun NoteContent(
    modifier: Modifier = Modifier,
    queryNotes: List<Note>,
    navController: NavController,
    paddingValues: PaddingValues,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        contentPadding = paddingValues
    ) {
        items(
            queryNotes.filter { !it.isPrivate },
            key = { note -> note.id }
        ) { note ->
            NoteItem(
                title = note.title,
                image = note.imageUri,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable { navController.navigate(Screens.NoteCreateEdit.rout + "?noteId=${note.id}") }
            )
        }
        repeat(20) {
            item {
                Spacer(modifier = modifier.height(15.dp))
            }
        }
    }
}
