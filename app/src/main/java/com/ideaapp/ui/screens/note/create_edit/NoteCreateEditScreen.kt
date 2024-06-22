package com.ideaapp.ui.screens.note.create_edit

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.component.NoteApp
import com.ideaapp.shared.compose.ui.components.IconComponentSwitcher
import com.ideaapp.shared.compose.ui.components.TopBar
import com.ideaapp.shared.compose.ui.navigation.components.NavController.Companion.canNavigate
import com.ideaapp.ui.screens.note.create_edit.component.NoteCreateEditComponent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteCreateEditScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: NoteCreateEditViewModel = koinViewModel(),
    context: Context,
) {
    val noteState by viewModel.noteState.collectAsState()

    var isPrivate by remember(noteState.isPrivate) {
        mutableStateOf(noteState.isPrivate)
    }
    var selectedImageUrl by remember(noteState.imageUri) {
        mutableStateOf(noteState.imageUri)
    }
    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if (it != null) {
                NoteApp.getUriPermission(it, context)
                viewModel.onEvent(NoteCreateEditUiEvent.UpdateUrl(it.toString()))
            }
            selectedImageUrl = it.toString()
        }
    )
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                NoteCreateEditViewModel.UiEvent.Delete -> {
                    navController.navigateUp()
                }

                NoteCreateEditViewModel.UiEvent.Save -> {
                    navController.navigateUp()
                }

                is NoteCreateEditViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.ime,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopBar(
                save = {
                    if (navController.canNavigate()) {
                        viewModel.onEvent(NoteCreateEditUiEvent.Save)
                    }
                },
                delete = {
                    if (navController.canNavigate()) {
                        viewModel.onEvent(NoteCreateEditUiEvent.Delete)
                    }
                },
                action = {
                    IconComponentSwitcher(
                        modifier = modifier,
                        checked = isPrivate,
                        onCheckedChange = {
                            isPrivate = !isPrivate
                            viewModel.onEvent(NoteCreateEditUiEvent.UpdatePrivate(isPrivate))
                        },
                        text = stringResource(id = R.string.secure_note),
                        icon = Icons.Default.Shield
                    )
                },
                additionalAction = {
                    IconButton(onClick = {
                        pickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) {
                        Icon(imageVector = Icons.Default.Image, contentDescription = "image")
                    }
                }
            )
        },
        content = {
            NoteCreateEditComponent(
                noteState = noteState,
                viewModel = viewModel,
                innerPadding = it,
                modifier = modifier,
                imageUrl = selectedImageUrl,
                pickerLauncher = pickerLauncher
            )
        }
    )
}