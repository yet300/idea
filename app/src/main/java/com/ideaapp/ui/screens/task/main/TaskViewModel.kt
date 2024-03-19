package com.ideaapp.ui.screens.task.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.ui.notification.ReminderNotification
import com.ideasapp.domain.model.Task
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
import kotlin.random.Random

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val reminderNotification: ReminderNotification
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private var deletedTask: Task? = null

    private fun generateNotificationId(): Long {
        return Random.nextLong()
    }

    init {
        loadTasks()
    }

    fun createTask(task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch {
            createTaskUseCase.invoke(task)
            if (task.reminderTime != null) {

                createReminderTask(
                    id = generateNotificationId(),
                    reminder = task.reminderTime!!,
                    name = task.name,
                    description = task.description!!
                )
            }
            onSuccess()
        }
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

    private fun createReminderTask(id: Long, reminder: Long, name: String, description: String) =
        viewModelScope.launch {
            reminderNotification.setReminder(id, reminder, name, description)
        }

    fun cancelReminderTask(id: Long) = viewModelScope.launch {
        reminderNotification.cancelReminder(id)
    }
}