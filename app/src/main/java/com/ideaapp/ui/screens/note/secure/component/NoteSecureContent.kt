package com.ideaapp.ui.screens.note.secure.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.screens.note.main.components.NoteItem
import com.ideasapp.domain.model.Note


@Composable
fun NoteSecureContent(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    paddingValues: PaddingValues,
    navController: NavController,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxWidth(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = paddingValues,
        verticalItemSpacing = 8.dp,
    ) {
        items(
            notes,
            key = { note -> note.id }) { note ->
            NoteItem(
                title = note.title,
                image = note.imageUri,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable { navController.navigate(Screens.NoteCreateEdit.rout + "?noteId=${note.id}") }
            )
        }

        repeat(20) {
            item {
                Spacer(modifier = modifier.height(20.dp))
            }
        }

    }
}

