package com.ideaapp.di

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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

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