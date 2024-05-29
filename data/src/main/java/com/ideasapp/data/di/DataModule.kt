package com.ideasapp.data.di

import android.content.Context
import com.ideasapp.data.IdeaDataBase
import com.ideasapp.data.dao.NoteDAO
import com.ideasapp.data.dao.ReminderDAO
import com.ideasapp.data.dao.TaskDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideAppDate(@ApplicationContext appContext: Context): IdeaDataBase =
        IdeaDataBase(appContext)


    @Provides
    fun provideNoteDao(appDatabase: IdeaDataBase): NoteDAO = appDatabase.noteDao


    @Provides
    fun provideTaskDao(appDatabase: IdeaDataBase): TaskDAO = appDatabase.taskDao



    @Provides
    fun providerReminderDao(appDatabase: IdeaDataBase): ReminderDAO = appDatabase.reminderDAO

}