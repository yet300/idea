package com.ideaapp.domain.task

import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.TaskRepository
import com.ideaapp.domain.usecase.task.UpdateTaskUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

//class UpdateTaskUseCaseTest {
//    private val taskRepository = mock<TaskRepository>()
//    private val useCase = UpdateTaskUseCase(taskRepository)
//
//    @Test
//    fun `test UpdateTask`() {
//        val testTask = Task(
//            name = "Test",
//            description = null,
//            isComplete = false
//        )
//
//
//        runBlocking {
//            Mockito.`when`(taskRepository.updateTask(testTask.id, true)).thenReturn(Unit)
//
//            useCase.invoke(testTask.id, true)
//
//            // Проверяем, что метод updateTask был вызван с правильными параметрами
//            verify(taskRepository).updateTask(testTask.id, true)
//
//
//        }
//
//
//    }
//}