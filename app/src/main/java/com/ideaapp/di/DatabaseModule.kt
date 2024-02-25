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
import com.ideasapp.domain.usecase.note.CreateNoteUseCase
import com.ideasapp.domain.usecase.note.DeleteNoteUseCase
import com.ideasapp.domain.usecase.note.GetNoteByIdUseCase
import com.ideasapp.domain.usecase.note.GetNoteUseCase
import com.ideasapp.domain.usecase.task.CreateTaskUseCase
import com.ideasapp.domain.usecase.task.DeleteTaskUseCase
import com.ideasapp.domain.usecase.task.GetTaskUseCase
import com.ideasapp.domain.usecase.task.UpdateTaskUseCase
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
    fun provideCreateNoteUseCase(
        noteRepository: NoteRepository
    ): CreateNoteUseCase {
        return CreateNoteUseCase(noteRepository)
    }

    @Provides
    fun provideGetNoteUseCase(
        noteRepository: NoteRepository
    ): GetNoteUseCase {
        return GetNoteUseCase(noteRepository)
    }

    @Provides
    fun provideDeleteNoteUseCase(
        noteRepository: NoteRepository
    ): DeleteNoteUseCase {
        return DeleteNoteUseCase(noteRepository)
    }

    @Provides
    fun provideGetNoteByIdNoteUseCase(
        noteRepository: NoteRepository
    ): GetNoteByIdUseCase {
        return GetNoteByIdUseCase(noteRepository)
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

    @Provides
    fun provideCreateTaskUseCase(
        taskRepository: TaskRepository
    ): CreateTaskUseCase {
        return CreateTaskUseCase(taskRepository)
    }

    @Provides
    fun provideDeleteTaskUseCase(
        taskRepository: TaskRepository
    ): DeleteTaskUseCase {
        return DeleteTaskUseCase(taskRepository)
    }

    @Provides
    fun provideGetTaskUseCase(
        taskRepository: TaskRepository
    ): GetTaskUseCase {
        return GetTaskUseCase(taskRepository)
    }

    @Provides
    fun provideUpdateTaskUseCase(
        taskRepository: TaskRepository
    ): UpdateTaskUseCase {
        return UpdateTaskUseCase(taskRepository)
    }

}
