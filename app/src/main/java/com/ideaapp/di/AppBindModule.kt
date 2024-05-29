package com.ideaapp.di

import com.ideaapp.utils.ManagerNotification
import com.ideaapp.utils.ManagerNotificationImpl
import com.ideaapp.utils.ReminderSchedulerRepositoryImpl
import com.ideasapp.domain.repository.ReminderSchedulerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface AppBindModule{

    @Binds
    fun bindReminderScheduler(reminderSchedulerRepositoryImpl: ReminderSchedulerRepositoryImpl): ReminderSchedulerRepository

    @Binds
    fun bindManagerNotification(managerNotificationImpl: ManagerNotificationImpl): ManagerNotification

}