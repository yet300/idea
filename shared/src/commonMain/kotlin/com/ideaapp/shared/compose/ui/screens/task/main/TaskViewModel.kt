package com.ideaapp.shared.compose.ui.screens.task.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasapp.domain.model.Task
import com.ideasapp.domain.usecase.task.CreateTaskUseCase
import com.ideasapp.domain.usecase.task.DeleteTaskUseCase
import com.ideasapp.domain.usecase.task.GetTaskUseCase
import com.ideasapp.domain.usecase.task.UpdateTaskUseCase
import com.ideasapp.domain.utils.memento.Caretaker
import com.ideasapp.domain.utils.memento.Originator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TaskViewModel(
    private val getTaskUseCase: GetTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val originator = Originator(Task())
    private val caretaker = Caretaker()

    init {
        loadTasks()
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
                originator.task = task
                caretaker.saveTask(originator)

                deleteTaskUseCase.invoke(task)
                delay(200)
                loadTasks()

            } catch (e: RuntimeException) {
                Log.e("TaskViewModel", "Error deleting task", e)
            }
        }
    }

    fun undoDeleteTask() = viewModelScope.launch {
        caretaker.undo(originator)
        createTaskUseCase.invoke(originator.task)
        loadTasks()
    }

}