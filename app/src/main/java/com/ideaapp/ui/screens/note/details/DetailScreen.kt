package com.ideaapp.ui.screens.note.details


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ideaapp.R
import com.ideaapp.ui.navigation.canGoBack
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.components.BackButton
import com.ideaapp.ui.components.ShowDialogConfirmation
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    id: String?,
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier
) {

    val note by viewModel.note.collectAsState()

    val htmlNote = note?.content ?: ""
    val state = rememberRichTextState()

    state.setHtml(htmlNote)


    val openAlertDialog = remember { mutableStateOf(false) }
    if (openAlertDialog.value) {
        ShowDialogConfirmation(
            onDismissRequest = {
                openAlertDialog.value = false
            },
            onConfirmation = {
                openAlertDialog.value = false
                viewModel.deleteNote {
                    navController.navigate(Screens.Home.rout)
                }
            },
            dialogText = stringResource(id = R.string.text_delete),
        )
    }



    id?.toLong()?.let { viewModel.getNoteById(id = it) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                },
                navigationIcon = {
                    BackButton(
                        onClick = {
                            if (navController.canGoBack) {
                                navController.popBackStack()
                            }
                        }
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                            openAlertDialog.value = true
                        }
                    ) {
                        Text(
                            stringResource(id = R.string.delete),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                },
                modifier = modifier,

                )
        },
        modifier = modifier
            .fillMaxSize(),
    ) { contentPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = contentPadding
        ) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(data = Uri.parse(note?.imageUri ?: ""))
                            .build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Text(
                    text = note?.title ?: "",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                )
            }
            item {
                Spacer(modifier = modifier.padding(10.dp))
            }
            item {
                RichText(
                    state = state,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                )
            }
        }

    }


}


