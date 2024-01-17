package com.ideaapp.di

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp : Application() {


    // Функция для получения разрешения на использование Uri
    companion object {
        fun getUriPermission(uri: Uri, context: Context) {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }

}