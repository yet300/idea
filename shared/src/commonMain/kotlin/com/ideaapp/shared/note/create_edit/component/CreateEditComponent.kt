package com.ideaapp.shared.note.create_edit.component

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ideaapp.domain.model.Note
import com.ideaapp.shared.compose.components.CustomTextField
import com.ideaapp.shared.note.create_edit.NoteCreateEditComponent
import com.ideaapp.shared.shared.GalleryPicker
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.change
import ideasapp.shared.generated.resources.note
import ideasapp.shared.generated.resources.title
import org.jetbrains.compose.resources.stringResource


@Composable
fun CreateEditComponent(
    modifier: Modifier = Modifier,
    noteState: Note,
    innerPadding: PaddingValues,
    imageUrl: String?,
    component: NoteCreateEditComponent,
    pickerLauncher: GalleryPicker
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight(),
        contentPadding = innerPadding,
        content = {
            item {
                if (imageUrl != null) {
                    Box(modifier = modifier.height(240.dp)) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        ElevatedButton(
                            content = {
                                Text(stringResource(Res.string.change))
                            },
                            onClick = {
                                pickerLauncher.launch()
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
                    onValueChange = { component.updateTitle(it) },
                    labelText = stringResource(Res.string.title),
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
                    onValueChange = { component.updateContent(it) },
                    labelText = stringResource(Res.string.note),
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