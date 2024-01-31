package com.ideaapp.presentation.screens.task.main.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp


@Composable
fun TaskItem(
    taskName: String,
    description: String,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {

    var checked by remember { mutableStateOf(isCompleted) }

    val textDecoration = if (checked) {
        AnnotatedString(
            text = taskName,
            spanStyle = SpanStyle(textDecoration = TextDecoration.LineThrough)
        )
    } else {
        AnnotatedString(taskName)
    }

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

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = textDecoration,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2
                    )

                    Text(
                        text = description,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 3
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}

