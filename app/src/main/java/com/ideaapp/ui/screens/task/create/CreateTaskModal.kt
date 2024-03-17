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
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.ideaapp.ui.components.TimePickerDialog
import com.ideaapp.ui.components.mToast
import com.ideaapp.ui.screens.note.create.components.CustomTextField
import java.text.SimpleDateFormat
import java.util.Date

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
        var showDateTimeButton by remember {
            mutableStateOf(false)
        }

        //date
        var showDatePicker by remember {
            mutableStateOf(false)
        }


        var selectedDate by remember { mutableStateOf("Select Date") }

        val datePickerState = rememberDatePickerState(
            yearRange = 2020..2060
        )


        //time
        var showTimePicker by remember {
            mutableStateOf(false)
        }
        var selectedTime by remember { mutableStateOf("Select Time") }

        var selectedHour by remember { mutableIntStateOf(0) }
        var selectedMinute by remember { mutableIntStateOf(0) }
        val timePickerState = rememberTimePickerState(
            initialHour = selectedHour,
            initialMinute = selectedMinute,
            is24Hour = true
        )

        val combinedDateTime = datePickerState.selectedDateMillis?.let { dateMillis ->
            timePickerState.hour.let { hour ->
                timePickerState.minute.let { minute ->
                    dateMillis + hour + minute
                }
            }
        } ?: 0L

        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            showDateTimeButton = isGranted
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


                if (showDateTimeButton) {

                    TextButton(onClick = { showDatePicker = true }) {
                        Text(text = selectedDate)
                    }
                    Spacer(modifier = modifier.padding(3.dp))

                    TextButton(onClick = { showTimePicker = true }) {
                        Text(text = selectedTime)

                    }


                }

                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                                        selectedDate = Tools.convertLongToTime(selectedDateMillis)
                                    }
                                    showDatePicker = false
                                }
                            ) { Text(stringResource(id = R.string.confirm)) }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showDatePicker = false
                                }
                            ) { Text(stringResource(id = R.string.cancel)) }
                        },
                        content = {
                            DatePicker(
                                state = datePickerState,
                                showModeToggle = false,
                            )

                        }
                    )
                }

                if (showTimePicker) {
                    TimePickerDialog(
                        title = "Select time",
                        onCancel = { showTimePicker = false },
                        onConfirm = {
                            selectedHour = timePickerState.hour
                            selectedMinute = timePickerState.minute
                            selectedTime = "${timePickerState.hour} : ${timePickerState.minute}"
                            showTimePicker = false
                        },
                        content = {
                            TimePicker(state = timePickerState)
                        }
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
                            showDateTimeButton = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.WatchLater,
                            contentDescription = null
                        )
                    }

                    TextButton(
                        onClick = {
                            if (taskName.isNotEmpty()) {
                                onCreateTask(taskName, taskDescription, combinedDateTime)
                                onDismiss()
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


object Tools {
    fun convertLongToTime(time: Long): String {

        val date = Date(time)
        val format = SimpleDateFormat("dd MM yyyy")
        return format.format(date)
    }
}



