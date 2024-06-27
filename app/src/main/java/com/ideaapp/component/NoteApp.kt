package com.ideaapp.component

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ideaapp.di.appModule
import com.ideaapp.di.dataModule
import com.ideaapp.di.viewModelModule
import com.ideaapp.shared.di.commonModule
import com.ideaapp.shared.di.domainModule
import com.ideaapp.utils.ManagerNotification
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteApp : Application() {

    private val notificationManager: ManagerNotification by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NoteApp)
            modules(appModule, dataModule, domainModule, viewModelModule, commonModule)
        }
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