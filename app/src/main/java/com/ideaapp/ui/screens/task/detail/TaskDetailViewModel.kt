package com.ideaapp.ui.screens.task.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasapp.domain.utils.InvalidException
import com.ideasapp.domain.model.Task
import com.ideasapp.domain.usecase.reminder.CreateReminderUseCase
import com.ideasapp.domain.usecase.reminder.DeleteReminderUseCase
import com.ideasapp.domain.usecase.task.CreateTaskUseCase
import com.ideasapp.domain.usecase.task.DeleteTaskUseCase
import com.ideasapp.domain.usecase.task.GetTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
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

    private var currentTaskId: Long? = null

    //TODO(исправить reminder)
    private var currentReminderTime: Long? = 0L

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Long>("taskId")?.let { taskId ->
            if (taskId != -1L) {
                viewModelScope.launch {
                    getTaskByIdUseCase.invoke(taskId)?.also { task ->
                        currentTaskId = task.id

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
                if (event.reminderTime != 0L) {
                    _taskState.value = _taskState.value.copy(
                        reminderTime = event.reminderTime,
                    )
                }
            }

            is TaskDetailUiEvent.CancelReminder -> viewModelScope.launch {
                if (event.reminderTime != 0L) {
                    deleteReminderUseCase.invoke(currentTaskId ?: 0L)
                }
            }

            TaskDetailUiEvent.Save -> {
                viewModelScope.launch {
                    try {
                        createTaskUseCase.invoke(
                            Task(
                                id = currentTaskId ?: 0L,
                                name = taskState.value.name,
                                description = taskState.value.description,
                                reminderTime = taskState.value.reminderTime,
                                isComplete = taskState.value.isComplete
                            )
                        )

//                        if (taskState.value.reminderTime != 0L) {
//                            createReminderUseCase.invoke(
//                                Reminder(
//                                    itemId = currentTaskId ?: 0L,
//                                    reminderTime = taskState.value.reminderTime ?: 0L,
//                                    name = taskState.value.name,
//                                    description = taskState.value.description!!,
//                                )
//                            )
//                        }
                        _eventFlow.emit(UiEvent.Save)
                    } catch (e: InvalidException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save"
                            )
                        )
                    }
                }
            }

            TaskDetailUiEvent.Delete -> {
                viewModelScope.launch {
                    try {
                        deleteTaskUseCase.invoke(
                            Task(currentTaskId ?: 0L)
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

//    private fun createReminderTask() = viewModelScope.launch {
//        createReminderUseCase.invoke(
//            Reminder(
//                itemId = currentTaskId ?: 0L,
//                reminderTime = _taskState.value.reminderTime ?: 0L,
//                name = _taskState.value.name,
//                description = _taskState.value.description!!,
//            )
//        )
//    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object Save : UiEvent()
        data object Delete : UiEvent()
    }
}
