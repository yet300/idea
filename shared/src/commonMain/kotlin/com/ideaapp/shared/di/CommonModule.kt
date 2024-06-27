package com.ideaapp.shared.di

import com.ideaapp.shared.note.main.DefaultNoteComponent
import org.koin.dsl.module

val commonModule = module {
    single {
        DefaultNoteComponent(
            componentContext = get()
        )
    }
}