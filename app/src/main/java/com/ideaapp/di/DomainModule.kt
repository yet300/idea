package com.ideaapp.di

import com.ideaapp.domain.usecase.note.CreateNoteUseCase
import com.ideaapp.domain.usecase.note.DeleteNoteUseCase
import com.ideaapp.domain.usecase.note.GetNoteByIdUseCase
import com.ideaapp.domain.usecase.note.GetNoteUseCase
import com.ideaapp.domain.usecase.reminder.CreateReminderUseCase
import com.ideaapp.domain.usecase.reminder.DeleteReminderUseCase
import com.ideaapp.domain.usecase.task.CreateTaskUseCase
import com.ideaapp.domain.usecase.task.DeleteTaskUseCase
import com.ideaapp.domain.usecase.task.GetTaskByIdUseCase
import com.ideaapp.domain.usecase.task.GetTaskUseCase
import com.ideaapp.domain.usecase.task.UpdateTaskUseCase
import org.koin.dsl.module


val domainModule = module {
    factory<CreateNoteUseCase> { CreateNoteUseCase(noteRepository = get()) }
    factory<DeleteNoteUseCase> { DeleteNoteUseCase(noteRepository = get()) }
    factory<GetNoteUseCase> { GetNoteUseCase(noteRepository = get()) }
    factory<GetNoteByIdUseCase> { GetNoteByIdUseCase(noteRepository = get()) }

    factory<CreateReminderUseCase> {
        CreateReminderUseCase(
            reminderRepository = get(),
            reminderScheduler = get()
        )
    }
    factory<DeleteReminderUseCase> {
        DeleteReminderUseCase(
            reminderRepository = get(),
            reminderScheduler = get()
        )
    }

    factory<CreateTaskUseCase> { CreateTaskUseCase(taskRepository = get()) }
    factory<DeleteTaskUseCase> {
        DeleteTaskUseCase(
            taskRepository = get(),
            reminderRepository = get()
        )
    }
    factory<GetTaskUseCase> { GetTaskUseCase(taskRepository = get()) }
    factory<GetTaskByIdUseCase> { GetTaskByIdUseCase(taskRepository = get()) }
    factory<UpdateTaskUseCase> { UpdateTaskUseCase(taskRepository = get()) }


}