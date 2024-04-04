package com.ideaapp.di

import android.app.AlarmManager
import android.content.Context
import com.ideaapp.utils.ManagerNotification
import com.ideaapp.utils.ManagerNotificationImpl
import com.ideaapp.utils.ReminderSchedulerImpl
import com.ideasapp.domain.utils.ReminderScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideReminderScheduler(@ApplicationContext context: Context): ReminderScheduler {
        return ReminderSchedulerImpl(context, provideAlarmManager(context))
    }

    @Singleton
    @Provides
    fun provideManagerNotification(@ApplicationContext context: Context): ManagerNotification {
        return ManagerNotificationImpl(context)
    }

    @Provides
    @Singleton
    fun provideAlarmManager(@ApplicationContext context: Context): AlarmManager {
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
}