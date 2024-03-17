package com.ideaapp.di

import android.content.Context
import com.ideaapp.ui.notification.ReminderManager
import com.ideaapp.ui.notification.ReminderNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideReminderNotification(@ApplicationContext context: Context): ReminderNotification {
        return ReminderManager(context)
    }
}