package com.ideaapp.shared.compose.screens.task.create_edit


sealed class TaskDetailUiEvent {
    data class UpdateName(val title: String) : TaskDetailUiEvent()

    data class UpdateDescription(val description: String) : TaskDetailUiEvent()

    data class UpdateComplete(val complete: Boolean) : TaskDetailUiEvent()

    data class UpdateReminder(val reminderTime: Long) :
        TaskDetailUiEvent()

    data class CancelReminder(val reminderTime: Long) : TaskDetailUiEvent()

    data object Delete : TaskDetailUiEvent()

    data object Save : TaskDetailUiEvent()

}