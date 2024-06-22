package com.ideaapp.ui.screens.note.create_edit.component


import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ideaapp.R
import com.ideaapp.shared.compose.ui.components.CustomTextField
import com.ideaapp.ui.screens.note.create_edit.NoteCreateEditUiEvent
import com.ideaapp.ui.screens.note.create_edit.NoteCreateEditViewModel
import com.ideasapp.domain.model.Note


@Composable
fun NoteCreateEditComponent(
    modifier: Modifier = Modifier,
    noteState: Note,
    innerPadding: PaddingValues,
    viewModel: NoteCreateEditViewModel,
    imageUrl: String?,
    pickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        contentPadding = innerPadding,
        content = {
            item {
                if (!imageUrl.isNullOrBlank()) {
                    Box(modifier = modifier.height(240.dp)) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        ElevatedButton(
                            content = {
                                Text(stringResource(id = R.string.change))
                            },
                            onClick = {
                                pickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                            modifier = modifier
                                .padding(16.dp)
                                .align(Alignment.BottomEnd)
                        )
                    }
                }
            }
            item {
                CustomTextField(
                    value = noteState.title,
                    onValueChange = { newTitle ->
                        viewModel.onEvent(NoteCreateEditUiEvent.UpdateTitle(newTitle))
                    },
                    labelText = stringResource(id = R.string.title),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = modifier
                        .fillMaxWidth(),
                )
            }
            item {
                CustomTextField(
                    value = noteState.content ?: "",
                    onValueChange = {
                        viewModel.onEvent(NoteCreateEditUiEvent.UpdateContent(it))
                    },
                    labelText = stringResource(id = R.string.Note),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = modifier
                        .fillMaxWidth(),
                )
            }
        }
    )
}