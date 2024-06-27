package com.ideaapp.shared.compose.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.create
import org.jetbrains.compose.resources.stringResource


@Composable
fun FAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(
            Icons.Filled.Create,
            stringResource(Res.string.create),
        )
    }
}