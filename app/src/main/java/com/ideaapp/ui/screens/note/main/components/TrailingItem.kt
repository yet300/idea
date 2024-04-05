package com.ideaapp.ui.screens.note.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ideaapp.R
import com.ideaapp.ui.components.TextIconComponentButton


@OptIn(ExperimentalMaterial3Api::class)
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
                modifier = modifier.padding(16.dp)
            ) {
                TextIconComponentButton(
                    content = stringResource(id = R.string.secure_note),
                    icon = Icons.Default.Shield,
                    onClick = { secureOnClick() }
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                TextIconComponentButton(
                    content = stringResource(id = R.string.settings),
                    icon = Icons.Default.Settings,
                    onClick = { settingsOnClick() }
                )
            }
        }
    }
}