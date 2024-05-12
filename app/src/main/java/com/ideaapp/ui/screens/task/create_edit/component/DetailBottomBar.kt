package com.ideaapp.ui.screens.task.create_edit.component

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ideaapp.R

@Composable
fun DetailBottomBar(
    complete: Boolean,
    onClick: () -> Unit
) {
    val textDecoration = if (complete) {
        stringResource(
            id = R.string.completed
        )
    } else {
        stringResource(
            id = R.string.uncompleted
        )
    }
    BottomAppBar(
        actions = {

        },
        floatingActionButton = {
            TextButton(
                onClick = onClick
            ) {
                Text(text = textDecoration)
            }
        }
    )
}