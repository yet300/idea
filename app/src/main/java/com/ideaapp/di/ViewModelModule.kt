package com.ideaapp.di

import com.ideaapp.ui.screens.note.create_edit.NoteCreateEditViewModel
import com.ideaapp.ui.screens.note.main.NoteViewModel
import com.ideaapp.ui.screens.note.secure.NoteSecureViewModel
import com.ideaapp.ui.screens.settings.SettingsViewModel
import com.ideaapp.ui.screens.task.create_edit.TaskDetailViewModel
import com.ideaapp.ui.screens.task.main.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::NoteViewModel)
    viewModelOf(::NoteCreateEditViewModel)
    viewModelOf(::NoteSecureViewModel)
    viewModelOf(::SettingsViewModel)

    viewModelOf(::SettingsViewModel)
    viewModelOf(::TaskViewModel)
    viewModelOf(::TaskDetailViewModel)
}