package com.ideaapp.ui.screens.task.main

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Task
import com.ideaapp.domain.usecases.task.CreateTaskUseCase
import com.ideaapp.domain.usecases.task.DeleteTaskUseCase
import com.ideaapp.domain.usecases.task.GetTaskUseCase
import com.ideaapp.domain.usecases.task.UpdateTaskUseCase
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
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private var deletedTask: Task? = null


    init {
        loadTasks()
    }


    private fun loadTasks() {
        viewModelScope.launch {
            getTaskUseCase.invoke().collect {
                _tasks.value = it
            }
        }
    }

    // Функция для обновления состояния задачи
    fun updateTaskComplete(id: Long, complete: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Вызов use case для обновления задачи
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
                // After deleting a task, load the updated task list
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
}