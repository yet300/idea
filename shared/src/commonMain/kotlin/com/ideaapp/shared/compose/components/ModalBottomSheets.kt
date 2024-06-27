
package com.ideaapp.shared.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ideasapp.shared.generated.resources.Res
import androidx.compose.ui.unit.dp

import ideasapp.shared.generated.resources.delete
import ideasapp.shared.generated.resources.secure_note
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteModelSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    delete: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit),
    modifier: Modifier = Modifier
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(),
            modifier = modifier.imePadding()
        ) {
            Column(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                content()
                IconComponentButton(
                    content = {
                        Text(
                            text = stringResource(Res.string.delete),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    icon = Icons.Default.Delete,
                    onClick = delete
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailingItem(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    secureOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(),
            modifier = modifier.imePadding()
        ) {
            Column(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                IconComponentButton(
                    content = {
                        Text(
                            text = stringResource(Res.string.secure_note),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    icon = Icons.Default.Shield,
                    onClick = { secureOnClick() }
                )
            }
        }
    }
}