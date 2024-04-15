@file:OptIn(ExperimentalMaterial3Api::class)

package com.ideaapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ideaapp.R


@Composable
fun DeleteModelSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    delete: () -> Unit,
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
                            text = stringResource(id = R.string.delete),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    icon = Icons.Default.Shield,
                    onClick = delete
                )
            }
        }
    }
}

@Composable
fun TrailingItem(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    secureOnClick: () -> Unit,
    settingsOnClick: () -> Unit,
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
                            text = stringResource(id = R.string.secure_note),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    icon = Icons.Default.Shield,
                    onClick = { secureOnClick() }
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                IconComponentButton(
                    content = {
                        Text(
                            text = stringResource(id = R.string.settings),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    icon = Icons.Default.Settings,
                    onClick = { settingsOnClick() }
                )
            }
        }
    }
}