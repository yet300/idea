package com.ideaapp.presentation.screens.task

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Task
import com.ideaapp.domain.usecases.task.CreateTaskUseCase
import com.ideaapp.domain.usecases.task.GetTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            try {
                val tasks = getTaskUseCase.invoke()
                _tasks.postValue(tasks)
                Log.d("TaskViewModel", "Tasks loaded successfully: $tasks")
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error loading tasks", e)
            }
        }
    }


    fun createTask(task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch {
            createTaskUseCase.invoke(task)
            onSuccess()
        }
    }

}