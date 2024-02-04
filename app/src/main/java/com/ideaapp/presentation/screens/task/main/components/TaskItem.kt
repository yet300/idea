package com.ideaapp.presentation.screens.task.main.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
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
import com.ideaapp.domain.model.Task


@Composable
fun TaskItem(
    task: Task,
    onTaskCheckedChange: (task: Task, checked: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    var isChecked by remember { mutableStateOf(task.isComplete) }


    val textDecoration = if (isChecked) {
        AnnotatedString(
            text = task.name,
            spanStyle = SpanStyle(textDecoration = TextDecoration.LineThrough)
        )
    } else {
        AnnotatedString(task.name)
    }

    Card(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { checked ->
                    onTaskCheckedChange(task, checked)
                    isChecked = checked
                }
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
                    text = task.description ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 3
                )
            }
        }
    }
}


