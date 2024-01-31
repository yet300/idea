package com.ideaapp.presentation.screens.task.create

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ideaapp.R
import com.ideaapp.domain.model.Task
import com.ideaapp.presentation.screens.note.create.components.CustomTextField
import com.ideaapp.presentation.ui.theme.components.mToast
import androidx.compose.ui.unit.dp


@Composable
fun CreateTaskScreen(
    onDismissRequest: () -> Unit,
    context: Context,
    viewModel: CreateTaskViewModel,
    modifier: Modifier = Modifier
) {
    var taskName by rememberSaveable { mutableStateOf("") }
    var taskDescription by rememberSaveable { mutableStateOf("") }



    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.TaskAlt, contentDescription = "Example Icon"
            )
        },
        title = {
            Text(text = "Create Task")
        },
        text = {
            Column {
                CustomTextField(
                    value = taskName,
                    onValueChange = {
                        taskName = it
                    },
                    labletext = stringResource(id = R.string.title),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(10.dp))

                CustomTextField(
                    value = taskDescription,
                    onValueChange = {
                        taskDescription = it
                    },
                    labletext = stringResource(id = R.string.description),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                )

            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (taskName.isNotEmpty()) {
                        viewModel.createTask(
                            Task(
                                name = taskName,
                                description = taskDescription
                            )
                        ) {
                            onDismissRequest()
                        }
                    } else {
                        mToast(context, context.getString(R.string.error_create))
                    }
                }
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}