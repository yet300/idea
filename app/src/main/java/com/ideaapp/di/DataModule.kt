package com.ideaapp.di

import com.ideasapp.data.IdeaDataBase
import com.ideasapp.data.dao.NoteDAO
import com.ideasapp.data.dao.ReminderDAO
import com.ideasapp.data.dao.TaskDAO
import com.ideasapp.data.repository.NoteRepositoryImpl
import com.ideasapp.data.repository.ReminderRepositoryImpl
import com.ideasapp.data.repository.TaskRepositoryImpl
import com.ideasapp.domain.repository.NoteRepository
import com.ideasapp.domain.repository.ReminderRepository
import com.ideasapp.domain.repository.TaskRepository
import org.koin.dsl.module


val dataModule = module {
    single<IdeaDataBase> { IdeaDataBase(applicationContext = get()) }

    single<NoteRepository> { NoteRepositoryImpl(dao = get()) }

    single<TaskRepository> { TaskRepositoryImpl(dao = get()) }

    single<ReminderRepository> { ReminderRepositoryImpl(dao = get()) }

    factory<NoteDAO> {
        val appDatabase = get<IdeaDataBase>()
        appDatabase.noteDao
    }

    factory<TaskDAO> {
        val appDatabase = get<IdeaDataBase>()
        appDatabase.taskDao
    }

    factory<ReminderDAO> {
        val appDatabase = get<IdeaDataBase>()
        appDatabase.reminderDAO
    }
}