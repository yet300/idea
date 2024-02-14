package com.ideaapp.presentation.screens.note.main.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ideaapp.R


@Composable
fun LargeFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeFloatingActionButton(
        onClick = { onClick() },
        modifier = modifier.padding(vertical = 80.dp)
    ) {
        Icon(
            Icons.Filled.Create,
            stringResource(id = R.string.create),
            modifier = modifier.size(FloatingActionButtonDefaults.LargeIconSize)
        )
    }
}