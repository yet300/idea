package com.ideaapp.shared.compose.ui.screens.task.create_edit.component

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.completed
import ideasapp.shared.generated.resources.uncompleted
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailBottomBar(
    complete: Boolean,
    onClick: () -> Unit
) {
    val textDecoration = if (complete) {
        stringResource(
            Res.string.completed
        )
    } else {
        stringResource(Res.string.uncompleted
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