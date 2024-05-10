package com.ideaapp.di

import android.app.AlarmManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.ideaapp.utils.ManagerNotification
import com.ideaapp.utils.ManagerNotificationImpl
import com.ideaapp.utils.ReminderSchedulerRepositoryImpl
import com.ideasapp.domain.repository.ReminderSchedulerRepository
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
    fun provideAppCompatActivity(@ApplicationContext context: Context): AppCompatActivity {
        return context as AppCompatActivity
    }

    @Singleton
    @Provides
    fun provideReminderScheduler(@ApplicationContext context: Context): ReminderSchedulerRepository {
        return ReminderSchedulerRepositoryImpl(context, provideAlarmManager(context))
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