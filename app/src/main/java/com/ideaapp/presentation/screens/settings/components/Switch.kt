package com.ideaapp.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SwitchSettings(
    modifier: Modifier = Modifier,
    content: String,
    icon: ImageVector,
    isChecked: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(imageVector = icon, contentDescription = null)

        Spacer(modifier = modifier.padding(8.dp))

        Column(
            modifier = modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Switch(
            checked = isChecked,
            onCheckedChange = { onClick() }
        )
    }
}
