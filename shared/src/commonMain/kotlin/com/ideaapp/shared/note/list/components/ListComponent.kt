package com.ideaapp.shared.note.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ideaapp.domain.model.Note
import com.ideaapp.shared.note.list.NoteListComponent


@Composable
fun ListComponent(
    modifier: Modifier = Modifier,
    queryNotes: List<Note>,
    paddingValues: PaddingValues,
    component: NoteListComponent
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxWidth(),
        columns = StaggeredGridCells.Fixed(2),
        state = rememberLazyStaggeredGridState(),
        verticalItemSpacing = 8.dp,
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
                    .clickable { component.onItemClicked(note.id) }
            )
        }
        repeat(20) {
            item {
                Spacer(modifier = modifier.height(15.dp))
            }
        }
    }
}
