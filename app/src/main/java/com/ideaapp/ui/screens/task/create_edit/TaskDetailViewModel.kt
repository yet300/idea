package com.ideaapp.ui.screens.task.create_edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Reminder
import com.ideaapp.domain.model.Task
import com.ideaapp.domain.usecase.reminder.CreateReminderUseCase
import com.ideaapp.domain.usecase.reminder.DeleteReminderUseCase
import com.ideaapp.domain.usecase.task.CreateTaskUseCase
import com.ideaapp.domain.usecase.task.DeleteTaskUseCase
import com.ideaapp.domain.usecase.task.GetTaskByIdUseCase
import com.ideaapp.domain.utils.InvalidException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val createReminderUseCase: CreateReminderUseCase,
    private val deleteReminderUseCase: DeleteReminderUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _taskState = MutableStateFlow(Task())
    val taskState: StateFlow<Task>
        get() = _taskState

    private var _currentTaskId: Long? = null
    val currentId: Long
        get() = _currentTaskId ?: 0L


    private var _currentReminderTime: Long? = null
    val currentReminderTime: Long
        get() = _currentReminderTime ?: 0L

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Long>("taskId")?.let { taskId ->
            if (taskId != -1L) {
                viewModelScope.launch {
                    getTaskByIdUseCase.invoke(taskId)?.also { task ->
                        _currentTaskId = task.id
                        _currentReminderTime = task.reminderTime

                        _taskState.value = taskState.value.copy(
                            name = task.name,
                            description = task.description ?: "",
                            isComplete = task.isComplete,
                            reminderTime = task.reminderTime
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: TaskDetailUiEvent) {
        when (event) {
            is TaskDetailUiEvent.UpdateName -> {
                _taskState.value = _taskState.value.copy(name = event.title)
            }

            is TaskDetailUiEvent.UpdateDescription -> {
                _taskState.value = _taskState.value.copy(description = event.description)

            }

            is TaskDetailUiEvent.UpdateComplete -> {
                _taskState.value = _taskState.value.copy(isComplete = event.complete)
            }

            is TaskDetailUiEvent.UpdateReminder -> {
                if (event.reminderTime == 0L) return
                viewModelScope.launch {
                    createReminderUseCase.invoke(
                        Reminder(
                            itemId = _currentTaskId ?: 0L,
                            reminderTime = event.reminderTime,
                            name = _taskState.value.name,
                            description = _taskState.value.description!!,
                        )
                    )
                    _taskState.value = _taskState.value.copy(
                        reminderTime = event.reminderTime,
                    )
                    _currentReminderTime = event.reminderTime
                }
            }

            is TaskDetailUiEvent.CancelReminder -> viewModelScope.launch {
                deleteReminderUseCase.invoke(_currentTaskId ?: 0L)
                _taskState.value = _taskState.value.copy(
                    reminderTime = 0L
                )
                _currentReminderTime = 0L
            }

            TaskDetailUiEvent.Save -> {
                viewModelScope.launch {
                    if (taskState.value.name.isNotEmpty()) {
                        createTaskUseCase.invoke(
                            Task(
                                id = _currentTaskId ?: 0L,
                                name = taskState.value.name,
                                description = taskState.value.description,
                                reminderTime = taskState.value.reminderTime,
                                isComplete = taskState.value.isComplete
                            )
                        )
                        _eventFlow.emit(UiEvent.Save)
                    } else {
                        deleteTaskUseCase.invoke(
                            Task(_currentTaskId ?: 0L)
                        )
                        _eventFlow.emit(UiEvent.Delete)
                    }
                }
            }

            TaskDetailUiEvent.Delete -> {
                viewModelScope.launch {
                    try {
                        deleteTaskUseCase.invoke(
                            Task(_currentTaskId ?: 0L)
                        )
                        _eventFlow.emit(UiEvent.Delete)
                    } catch (e: InvalidException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't deleted "
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object Save : UiEvent()
        data object Delete : UiEvent()
    }
}
