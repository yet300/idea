package com.ideaapp.presentation.screens.create


import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ideaapp.domain.model.Note
import com.ideaapp.R
import com.ideaapp.di.NoteApp
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.create.components.CustomTextField
import com.ideaapp.presentation.screens.create.components.EditorControls
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<CreateViewModel>()

    var title by rememberSaveable { mutableStateOf("") }
    val description = rememberRichTextState()


    val titleSize = MaterialTheme.typography.displaySmall.fontSize
    val subtitleSize = MaterialTheme.typography.titleLarge.fontSize

    var isTextFieldFocused by remember { mutableStateOf(false) }

    var selectedImageUrl by remember {
        mutableStateOf<Uri?>(null)
    }


    //Select Image in Gallery
    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if (it != null) {
                NoteApp.getUriPermission(it, context)
            }
            selectedImageUrl = it
        }
    )



    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    if (title.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.title),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    } else {
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = null
                        )
                    }
                },
                actions = {
                    Text(
                        text = stringResource(id = R.string.create),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = modifier
                            .clickable {
                                if (title.isNotEmpty()) {
                                    viewModel.createNote(
                                        Note(
                                            title = title,
                                            content = description.toHtml(),
                                            imageUri = selectedImageUrl.toString()
                                        )
                                    ) {
                                        navController.navigate(Screens.Home.rout)
                                    }
                                } else {
                                    mToast(context, context.getString(R.string.error_create))
                                }
                            }
                            .padding(6.dp)
                    )
                },
                modifier = modifier
            )


        },
        content = { contentPadding ->
            Column(
                modifier = modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxHeight()
                        .weight(9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    contentPadding = PaddingValues(3.dp),
                    content = {
                        // Отображаем изображение с кнопкой изменения
                        item {
                            if (selectedImageUrl != null) {
                                Box(
                                    modifier = modifier.height(240.dp),
                                ) {
                                    AsyncImage(
                                        model = selectedImageUrl,
                                        contentDescription = null,
                                        modifier = modifier,
                                        contentScale = ContentScale.Crop
                                    )
                                    ElevatedButton(
                                        onClick = {
                                            pickerLauncher.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                            )
                                        },
                                        modifier = modifier
                                            .padding(16.dp) // Устанавливаем отступы
                                            .align(Alignment.BottomEnd) // Выравниваем по верхнему левому углу
                                    ) {
                                        Text(stringResource(id = R.string.change))
                                    }
                                }
                            }
                        }

                        // Отображаем редактор
                        item {
                            EditorControls(
                                modifier = modifier
                                    .fillMaxWidth(),
                                onBoldClick = {
                                    description.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
                                },
                                onItalicClick = {
                                    description.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic))
                                },
                                onUnderlineClick = {
                                    description.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                                },
                                onTitleClick = {
                                    description.toggleSpanStyle(SpanStyle(fontSize = titleSize))
                                },
                                onSubtitleClick = {
                                    description.toggleSpanStyle(SpanStyle(fontSize = subtitleSize))
                                },
                                onStartAlignClick = {
                                    description.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Start))
                                },
                                onEndAlignClick = {
                                    description.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.End))
                                },
                                onCenterAlignClick = {
                                    description.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
                                },
                                onUnorderedListClick = {
                                    description.toggleOrderedList()
                                },
                                onOrderClick = {
                                    description.toggleUnorderedList()
                                }
                            )
                        }

                        // Отображаем кнопку для добавления обложки, если изображение не выбрано
                        if (selectedImageUrl == null) {
                            item {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    TextButton(
                                        onClick = {
                                            pickerLauncher.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                            )
                                        }
                                    ) {
                                        Text(stringResource(id = R.string.cover))
                                    }
                                }
                            }
                        }

                        // Отображаем текстовое поле для ввода заголовка
                        item {
                            CustomTextField(
                                value = title,
                                onValueChange = {
                                    title = it
                                    isTextFieldFocused = it.isNotEmpty()
                                },
                                labletext = stringResource(id = R.string.title),
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                ),
                                modifier = modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight(0.3f),
                            )
                        }

                        // Отображаем редактор богатого текста
                        item {
                            RichTextEditor(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .imePadding()
                                    .height(1500.dp)
                                    .weight(1f),
                                state = description,
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.note),
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                            )
                        }
                    }
                )
            }
        },
        modifier = modifier
            .fillMaxSize()
    )
}

private fun mToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}





