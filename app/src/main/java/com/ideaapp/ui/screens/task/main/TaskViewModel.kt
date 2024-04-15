package com.ideaapp.ui.screens.task.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasapp.domain.model.Reminder
import com.ideasapp.domain.model.Task
import com.ideasapp.domain.usecase.reminder.CreateReminderUseCase
import com.ideasapp.domain.usecase.task.CreateTaskUseCase
import com.ideasapp.domain.usecase.task.DeleteTaskUseCase
import com.ideasapp.domain.usecase.task.GetTaskUseCase
import com.ideasapp.domain.usecase.task.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val createReminderUseCase: CreateReminderUseCase,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private var deletedTask: Task? = null

    init {
        loadTasks()
    }

    fun createTask(task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch {
            createTaskUseCase.invoke(task)
            onSuccess()
        }
    }

    private fun loadTasks() {
        viewModelScope.launch(Dispatchers.Main) {
            getTaskUseCase.invoke().collect {
                _tasks.value = it
            }
        }
    }

    fun updateTaskComplete(id: Long, complete: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateTaskUseCase.invoke(id, complete)

                delay(200)
                loadTasks()
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error updating task", e)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deletedTask = task
                deleteTaskUseCase.invoke(task)
                delay(200)
                loadTasks()

            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error deleting task", e)
            }
        }
    }

    fun undoDeleteTask() = deletedTask?.let { task ->
        viewModelScope.launch(Dispatchers.IO) {
            createTaskUseCase.invoke(task)
            loadTasks()
        }
    }

    fun createReminderTask(reminder: Reminder) =
        viewModelScope.launch {
            createReminderUseCase.invoke(reminder)
        }
}