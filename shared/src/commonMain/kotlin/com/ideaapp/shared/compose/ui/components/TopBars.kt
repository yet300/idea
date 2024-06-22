package com.ideaapp.shared.compose.ui.components


import androidx.compose.foundation.layout.ColumnScope
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
import com.ideaapp.shared.compose.ui.components.BackButton
import com.ideaapp.shared.compose.ui.components.DeleteModelSheet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    save: () -> Unit,
    delete: () -> Unit,
    action: @Composable (ColumnScope.() -> Unit),
    additionalAction: (@Composable () -> Unit)? = null,
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
            additionalAction?.invoke()

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
                content = action,
                modifier = modifier
            )
        },
        modifier = modifier,
    )
}