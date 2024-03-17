package com.ideaapp.ui.screens.task.main.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ideaapp.ui.screens.task.create.Tools
import com.ideasapp.domain.model.Task


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    task: Task,
    onTaskCheckedChange: (checked: Boolean) -> Unit,
    onDelete: (focusPreviousItem: Boolean) -> Unit,
    reminder: Long?,
    CancelReminder: () -> Unit,
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


    val dismissState = rememberSwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
        confirmValueChange = { value ->
            // Обработка события отклонения элемента
            if (value != SwipeToDismissBoxValue.Settled) {
                onDelete(false)
                true
            } else {
                false
            }
        },
        positionalThreshold = { distance -> distance * 0.33f }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {}
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .alpha(if (dismissState.progress == 1f) 1f else 1f - dismissState.progress)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { checked ->
                    onTaskCheckedChange(checked)
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
                )
                if (reminder != null) {
                    SuggestionChip(
                        onClick = {
                            CancelReminder()
                        },
                        label = { Text(Tools.convertLongToTime(reminder)) }
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}



