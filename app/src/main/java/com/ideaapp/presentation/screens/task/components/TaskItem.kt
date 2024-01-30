package com.ideaapp.presentation.screens.task.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TaskItem(
    taskName: String,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {

    var checked by remember { mutableStateOf(isCompleted) }


    Box(modifier = modifier.fillMaxWidth()) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        checked = isChecked

                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = taskName,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}

