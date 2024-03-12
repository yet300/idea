package com.ideaapp.ui.screens.task.create

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.ideaapp.ui.components.mToast
import com.ideaapp.ui.screens.note.create.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskModal(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    onCreateTask: (String, String) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(),
            modifier = modifier.imePadding()
        ) {
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                var taskName by remember { mutableStateOf("") }
                var taskDescription by remember { mutableStateOf("") }
                var showTextFiled by remember {
                    mutableStateOf(false)
                }

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
                    TextButton(
                        onClick = {
                            if (taskName.isNotEmpty()) {
                                onCreateTask(taskName, taskDescription)
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


