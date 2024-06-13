package com.ideaapp.di

import android.app.AlarmManager
import android.content.Context
import com.ideaapp.utils.ManagerNotification
import com.ideaapp.utils.ManagerNotificationImpl
import com.ideaapp.utils.ReminderSchedulerRepositoryImpl
import com.ideasapp.domain.repository.ReminderSchedulerRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory<ManagerNotification> { ManagerNotificationImpl(context = get()) }
    factory<ReminderSchedulerRepository> {
        ReminderSchedulerRepositoryImpl(
            context = get(),
            alarmManager = get()
        )
    }
    single<AlarmManager> { androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager }
}