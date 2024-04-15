package com.ideaapp.ui.screens.task.detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ideaapp.ui.components.BackButton
import com.ideaapp.ui.components.DeleteModelSheet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    save: () -> Unit,
    delete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {

        },
        navigationIcon = {
            BackButton(
                onClick = save
            )
        },
        actions = {
            IconButton(onClick = {
                showBottomSheet = true
            }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert, contentDescription = null
                )
            }
            DeleteModelSheet(
                showBottomSheet = showBottomSheet,
                onDismiss = { showBottomSheet = false },
                delete = delete,
                modifier = modifier
            )
        },
        modifier = modifier,
    )
}