package com.ideaapp.presentation.screens.task.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Task
import com.ideaapp.domain.usecases.task.GetTaskUseCase
import com.ideaapp.domain.usecases.task.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TaskViewState())
    val state: StateFlow<TaskViewState>
        get() = _state

    val selected = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            combine(getTaskUseCase.invoke(), selected) { taskList: List<Task>, selected: Boolean ->
                TaskViewState(taskList, selected)
            }.collect {
                _state.value = it
            }
        }
    }

    // Функция для обновления состояния задачи
    fun updateTaskComplete(task: Task, complete: Boolean) {
        viewModelScope.launch {
            try {
                // Вызов use case для обновления задачи
                updateTaskUseCase.invoke(task.copy(isComplete = complete))

                selected.value = complete
                _state.value = _state.value.copy(selected = complete)
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error updating task", e)
            }
        }
    }
}

data class TaskViewState(
    var taskList: List<Task> = emptyList(),
    val selected: Boolean = false,
)