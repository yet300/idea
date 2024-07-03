package com.ideaapp.domain.task

import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.TaskRepository
import com.ideaapp.domain.usecase.task.CreateTaskUseCase
import io.mockative.classOf
import io.mockative.mock
import io.mockative.verify
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

//class CreateTaskUseCaseTest {
//    private val taskRepository = mock<TaskRepository>(classOf())
//    private val useCase = CreateTaskUseCase(taskRepository)
//
//    @Test
//    fun `test CreateTask`() {
//        val testTask = Task(
//            id = 0,
//            name = "Test0",
//            description = null
//        )
//
//        runBlocking {
//            useCase.invoke(testTask)
//
//            verify(taskRepository).insertTask(testTask)
//        }
//    }
//
//}