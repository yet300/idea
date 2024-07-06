package com.ideaapp.shared.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun rememberGalleryManager(onResult: (SharedImage) -> Unit): GalleryPicker

expect class GalleryPicker(
    onLaunch: () -> Unit
) {
    fun launch()
}

expect class SharedImage {
    fun toByteArray(): ByteArray?
    fun toImageBitmap(): ImageBitmap?
}