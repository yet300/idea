package com.ideaapp.ui.screens.task.detail.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ideaapp.R
import com.ideaapp.ui.components.CustomTextField
import com.ideaapp.ui.components.DateTimeConvertor
import com.ideaapp.ui.components.DateTimeDialog
import com.ideaapp.ui.components.IconComponentButton
import com.ideaapp.ui.components.TextIconComponentTextField
import com.ideaapp.ui.screens.task.detail.TaskDetailUiEvent
import com.ideaapp.ui.screens.task.detail.TaskDetailViewModel
import com.ideasapp.domain.model.Task

@Composable
fun TaskDetailComponent(
    viewModel: TaskDetailViewModel,
    taskState: Task,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    var showDateTimeDialog by remember {
        mutableStateOf(false)
    }
    var createdReminder by remember(taskState.reminderTime) {
        mutableStateOf(taskState.reminderTime)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = innerPadding
    ) {
        item {
            CustomTextField(
                value = taskState.name,
                onValueChange = { newName ->
                    viewModel.onEvent(
                        TaskDetailUiEvent.UpdateName(
                            newName
                        )
                    )
                },
                labelText = stringResource(id = R.string.title),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }

        item {
            TextIconComponentTextField(
                value = taskState.description ?: "",
                onValueChange = { newDescription ->
                    viewModel.onEvent(TaskDetailUiEvent.UpdateDescription(newDescription))
                },
                icon = Icons.Outlined.ChatBubbleOutline,
                label = stringResource(id = R.string.description),
            )
        }
        item {
            if (showDateTimeDialog) {
                DateTimeDialog(
                    onCancel = { showDateTimeDialog = false },
                    onConfirm = { dateTime ->
                        createdReminder = dateTime
                        viewModel.onEvent(TaskDetailUiEvent.UpdateReminder(createdReminder ?: 0L))
                        showDateTimeDialog = false
                    },
                    context = LocalContext.current
                )
            }
            IconComponentButton(
                icon = Icons.Outlined.WatchLater,
                content = {
                    if (createdReminder != 0L) {
                        InputChip(
                            selected = false,
                            onClick = {
                                viewModel.onEvent(
                                    TaskDetailUiEvent.CancelReminder(
                                        createdReminder ?: 0L
                                    )
                                )
                                createdReminder = 0L
                            },
                            label = {
                                Text(
                                    DateTimeConvertor.convertLongToDateTime(
                                        createdReminder ?: 0L
                                    )
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null
                                )

                            }
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.reminder),
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            fontSize = 20.sp,
                        )
                    }
                },
                onClick = {
                    showDateTimeDialog = true
                }
            )
        }
    }
}