package com.ideasapp.data.di

import com.ideasapp.data.repository.NoteRepositoryImpl
import com.ideasapp.data.repository.ReminderRepositoryImpl
import com.ideasapp.data.repository.TaskRepositoryImpl
import com.ideasapp.domain.repository.NoteRepository
import com.ideasapp.domain.repository.ReminderRepository
import com.ideasapp.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataBindModule {

    @Binds
    fun bindNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository

    @Binds
    fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository

    @Binds
    fun bindReminderRepository(reminderRepositoryImpl: ReminderRepositoryImpl): ReminderRepository
}