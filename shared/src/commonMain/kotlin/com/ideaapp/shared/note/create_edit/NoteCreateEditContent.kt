package com.ideaapp.shared.note.create_edit

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.ideaapp.shared.compose.components.IconComponentSwitcher
import com.ideaapp.shared.compose.components.TopBar
import com.ideaapp.shared.note.create_edit.component.CreateEditComponent
import com.ideaapp.shared.shared.rememberGalleryManager
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.secure_note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoteCreateEditContent(
    modifier: Modifier = Modifier,
    component: NoteCreateEditComponent,
) {

    val noteState by component.model.subscribeAsState()

    var isPrivate by remember(noteState.isPrivate) {
        mutableStateOf(noteState.isPrivate)
    }
    var selectedImageUrl by remember(noteState.imageUri) {
        mutableStateOf(noteState.imageUri)
    }

    val coroutineScope = rememberCoroutineScope()
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var launchGallery by remember { mutableStateOf(value = false) }

//    if (launchGallery) {
//        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
//            galleryManager.launch()
//        } else {
//            permissionsManager.askPermission(PermissionType.GALLERY)
//        }
//        launchGallery = false
//    }

    val galleryManager = rememberGalleryManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                it.toImageBitmap()
            }
            imageBitmap = bitmap
            selectedImageUrl = bitmap.toString()
        }
    }
//    val pickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = {
//            if (it != null) {
//                component.updateImage(it)
//            }
//            selectedImageUrl = it.toString()
//        }
//    )
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.ime,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopBar(
                save = {
                    component.onBackClick()
                },
                delete = {
                    component.delete(noteState.id)
                },
                action = {
                    IconComponentSwitcher(
                        modifier = modifier,
                        checked = isPrivate,
                        onCheckedChange = {
                            isPrivate = !isPrivate
                            component.updatePrivate(it)
                        },
                        text = stringResource(Res.string.secure_note),
                        icon = Icons.Default.Shield
                    )
                },
                additionalAction = {
                    IconButton(onClick = {
                        galleryManager.launch()
                    }) {
                        Icon(imageVector = Icons.Default.Image, contentDescription = "image")
                    }
                }
            )
        },
        content = {
            CreateEditComponent(
                noteState = noteState,
                innerPadding = it,
                modifier = modifier,
                imageUrl = selectedImageUrl,
                component = component,
                pickerLauncher = galleryManager
            )
        }
    )
}