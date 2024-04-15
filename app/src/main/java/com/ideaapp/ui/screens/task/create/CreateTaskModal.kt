package com.ideaapp.ui.screens.task.create

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ideaapp.R
import com.ideaapp.ui.components.DateTimeConvertor
import com.ideaapp.ui.components.DateTimeDialog
import com.ideaapp.ui.components.mToast
import com.ideaapp.ui.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskModal(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    onCreateTask: (String, String, Long) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    if (showBottomSheet) {
        var taskName by remember { mutableStateOf("") }
        var taskDescription by remember { mutableStateOf("") }
        var showTextFiled by remember {
            mutableStateOf(false)
        }

        //Date and Time
        var showDateTimeDialog by remember {
            mutableStateOf(false)
        }
        var combinedDateTime by remember { mutableLongStateOf(0L) }


        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            showDateTimeDialog = isGranted
        }
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(),
            modifier = modifier.imePadding()
        ) {
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                CustomTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    labelText = stringResource(id = R.string.title),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = modifier.fillMaxWidth()
                )

                Spacer(modifier = modifier.padding(6.dp))

                if (showTextFiled) {
                    CustomTextField(
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        labelText = stringResource(id = R.string.description),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = modifier.fillMaxWidth()
                    )
                }

                if (combinedDateTime != 0L) {
                    SuggestionChip(
                        onClick = { showDateTimeDialog = true },
                        label = { Text(DateTimeConvertor.convertLongToDateTime(combinedDateTime)) }
                    )
                }
                if (showDateTimeDialog) {
                    DateTimeDialog(
                        onCancel = { showDateTimeDialog = false },
                        onConfirm = { dateTime ->
                            combinedDateTime = dateTime
                            showDateTimeDialog = false
                        },
                        context = context
                    )
                }



                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {

                    IconButton(onClick = { showTextFiled = true }) {
                        Icon(
                            imageVector = Icons.Filled.ChatBubbleOutline,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        } else {
                            showDateTimeDialog = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.WatchLater,
                            contentDescription = null
                        )
                    }

                    TextButton(
                        onClick = {
                            if (taskName.isNotEmpty()) {
                                if (combinedDateTime != 0L) {
                                    onCreateTask(taskName, taskDescription, combinedDateTime)
                                    onDismiss()
                                } else {
                                    onCreateTask(taskName, taskDescription, 0L)
                                    onDismiss()
                                }
                            } else {
                                mToast(
                                    context,
                                    context.getString(R.string.error_create)
                                )
                            }

                        }
                    ) {
                        Text(
                            stringResource(id = R.string.create),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}



