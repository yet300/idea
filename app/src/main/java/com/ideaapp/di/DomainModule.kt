package com.ideaapp.di

import com.ideasapp.domain.usecase.note.CreateNoteUseCase
import com.ideasapp.domain.usecase.note.DeleteNoteUseCase
import com.ideasapp.domain.usecase.note.GetNoteByIdUseCase
import com.ideasapp.domain.usecase.note.GetNoteUseCase
import com.ideasapp.domain.usecase.reminder.CreateReminderUseCase
import com.ideasapp.domain.usecase.reminder.DeleteReminderUseCase
import com.ideasapp.domain.usecase.task.CreateTaskUseCase
import com.ideasapp.domain.usecase.task.DeleteTaskUseCase
import com.ideasapp.domain.usecase.task.GetTaskByIdUseCase
import com.ideasapp.domain.usecase.task.GetTaskUseCase
import com.ideasapp.domain.usecase.task.UpdateTaskUseCase
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