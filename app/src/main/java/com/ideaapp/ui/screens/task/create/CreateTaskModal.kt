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
import com.ideaapp.ui.components.DateTimeConvertor
import com.ideaapp.ui.components.DateTimeConvertor.isValidDateTime
import com.ideaapp.ui.components.mToast
import com.ideaapp.ui.screens.note.create.components.CustomTextField
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Calendar

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
        val currentDateTime = LocalDateTime.now()
        val date = remember {
            Calendar.getInstance().apply {
                set(Calendar.YEAR, currentDateTime.year)
                set(Calendar.MONTH, currentDateTime.monthValue - 1)
                set(Calendar.DAY_OF_MONTH, currentDateTime.dayOfMonth)
            }.timeInMillis
        }
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = date,
            yearRange = 2020..2060
        )


        //time
        var showTimePicker by remember {
            mutableStateOf(false)
        }

        val currentTime = LocalTime.now()
        val timeInMillis = remember {
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, currentTime.hour)
                set(Calendar.MINUTE, currentTime.minute)
            }.timeInMillis
        }

        var selectedTime by remember { mutableStateOf("Select Time") }


        val timePickerState = rememberTimePickerState(
            initialHour = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timeInMillis),
                ZoneId.systemDefault()
            ).hour,
            initialMinute = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timeInMillis),
                ZoneId.systemDefault()
            ).minute,
            is24Hour = true
        )

        //Соединение даты и времени
        val combinedDateTime = datePickerState.selectedDateMillis?.let { dateMillis ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dateMillis
            calendar.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
            calendar.set(Calendar.MINUTE, timePickerState.minute)
            calendar.timeInMillis
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
                                        selectedDate = DateTimeConvertor.convertLongToDate(selectedDateMillis)
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
                            timePickerState.let { time ->
                                selectedTime = DateTimeConvertor.convertIntToTime(time.hour, time.minute)
                            }
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
                                if (isValidDateTime(combinedDateTime)) {
                                    onCreateTask(taskName, taskDescription, combinedDateTime)
                                    onDismiss()
                                } else {
                                    mToast(context, context.getString(R.string.error_invalid_datetime))
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



