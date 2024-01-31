package com.ideaapp.presentation.screens.task.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaapp.domain.model.Task
import com.ideaapp.domain.usecases.task.CreateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,

    ) : ViewModel() {

    fun createTask(task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch {
            createTaskUseCase.invoke(task)
            onSuccess()
        }
    }
}