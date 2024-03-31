package com.ideaapp.di

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ideaapp.utils.ManagerNotification
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NoteApp : Application() {


    @Inject
    lateinit var notificationManager: ManagerNotification

    override fun onCreate() {
        super.onCreate()
        notificationManager.init()
    }
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