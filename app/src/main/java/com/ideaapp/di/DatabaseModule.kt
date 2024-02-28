package com.ideaapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.ideasapp.data.IdeaDataBase
import com.ideasapp.data.dao.NoteDAO
import com.ideasapp.data.dao.TaskDAO
import com.ideasapp.data.repository.NoteRepositoryImpl
import com.ideasapp.data.repository.TaskRepositoryImpl
import com.ideasapp.domain.repository.NoteRepository
import com.ideasapp.domain.repository.TaskRepository
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideAppDate(@ApplicationContext appContext: Context): IdeaDataBase {
        return IdeaDataBase(appContext)
    }


    @Provides
    @Singleton
    fun provideNoteRepository(appDatabase: IdeaDataBase): NoteRepository {
        return NoteRepositoryImpl(appDatabase.noteDao)
    }



    @Provides
    fun provideNoteDao(appDatabase: IdeaDataBase): NoteDAO {
        return appDatabase.noteDao
    }


    @Provides
    @Singleton
    fun provideTaskRepository(appDatabase: IdeaDataBase): TaskRepository {
        return TaskRepositoryImpl(appDatabase.taskDao)
    }


    @Provides
    fun provideTaskDao(appDatabase: IdeaDataBase): TaskDAO {
        return appDatabase.taskDao
    }


}
