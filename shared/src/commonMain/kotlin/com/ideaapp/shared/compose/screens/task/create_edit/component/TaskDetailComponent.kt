package com.ideaapp.shared.compose.screens.task.create_edit.component

//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.outlined.ChatBubbleOutline
//import androidx.compose.material.icons.outlined.WatchLater
//import androidx.compose.material3.Icon
//import androidx.compose.material3.InputChip
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.sp
//import com.ideaapp.shared.compose.components.CustomTextField
//import com.ideaapp.shared.compose.components.DateTimeConvertor
//import com.ideaapp.shared.compose.components.DateTimeDialog
//import com.ideaapp.shared.compose.components.IconComponentButton
//import com.ideaapp.shared.compose.components.TextIconComponentTextField
//import com.ideaapp.shared.compose.ui.screens.task.create_edit.TaskDetailUiEvent
//import com.ideaapp.shared.compose.ui.screens.task.create_edit.TaskDetailViewModel
//import ideasapp.shared.generated.resources.Res
//import ideasapp.shared.generated.resources.description
//import ideasapp.shared.generated.resources.reminder
//import ideasapp.shared.generated.resources.title
//import org.jetbrains.compose.resources.stringResource
//
//@Composable
//fun TaskDetailComponent(
//    viewModel: TaskDetailViewModel,
//    taskState: Task,
//    innerPadding: PaddingValues,
//    modifier: Modifier = Modifier
//) {
//    var showDateTimeDialog by remember {
//        mutableStateOf(false)
//    }
////    var createdReminder by remember(taskState.reminderTime) {
////        mutableStateOf(taskState.reminderTime)
////    }
//
//    LazyColumn(
//        modifier = modifier
//            .fillMaxWidth(),
//        contentPadding = innerPadding
//    ) {
//        item {
//            CustomTextField(
//                value = taskState.name,
//                onValueChange = { newName ->
//                    viewModel.onEvent(
//                        TaskDetailUiEvent.UpdateName(
//                            newName
//                        )
//                    )
//                },
//                labelText = stringResource(Res.string.title),
//                textStyle = TextStyle(
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//            )
//        }
//
//        item {
//            TextIconComponentTextField(
//                value = taskState.description ?: "",
//                onValueChange = { newDescription ->
//                    viewModel.onEvent(TaskDetailUiEvent.UpdateDescription(newDescription))
//                },
//                icon = Icons.Outlined.ChatBubbleOutline,
//                label = stringResource(Res.string.description),
//            )
//        }
//        item {
//            if (showDateTimeDialog) {
//                DateTimeDialog(
//                    onCancel = { showDateTimeDialog = false },
//                    onConfirm = { dateTime ->
//                        viewModel.onEvent(TaskDetailUiEvent.UpdateReminder(dateTime))
//                        showDateTimeDialog = false
//                    },
//                    context = LocalContext.current
//                )
//            }
//            IconComponentButton(
//                icon = Icons.Outlined.WatchLater,
//                content = {
//                    if (viewModel.currentReminderTime != 0L) {
//                        InputChip(
//                            selected = false,
//                            onClick = {
//                                viewModel.onEvent(
//                                    TaskDetailUiEvent.CancelReminder(
//                                        viewModel.currentReminderTime
//                                    )
//                                )
//                            },
//                            label = {
//                                Text(
//                                    DateTimeConvertor.convertLongToDateTime(
//                                        viewModel.currentReminderTime
//                                    )
//                                )
//                            },
//                            trailingIcon = {
//                                Icon(
//                                    imageVector = Icons.Default.Close,
//                                    contentDescription = null
//                                )
//
//                            }
//                        )
//                    } else {
//                        Text(
//                            text = stringResource(Res.string.reminder),
//                            style = TextStyle(
//                                fontWeight = FontWeight.Medium,
//                                color = MaterialTheme.colorScheme.onSurface
//                            ),
//                            fontSize = 20.sp,
//                        )
//                    }
//                },
//                onClick = {
//                    showDateTimeDialog = true
//                }
//            )
//        }
//    }
//}