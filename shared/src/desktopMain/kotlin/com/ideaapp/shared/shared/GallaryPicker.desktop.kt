package com.ideaapp.shared.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

actual class GalleryPicker actual constructor(onLaunch: () -> Unit) {
    actual fun launch() {
    }
}

actual class SharedImage {
    actual fun toByteArray(): ByteArray? {
        TODO("Not yet implemented")
    }

    actual fun toImageBitmap(): ImageBitmap? {
        TODO("Not yet implemented")
    }
}

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage) -> Unit): GalleryPicker {
    TODO("Not yet implemented")
}