package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.model.Task
import com.ideasapp.domain.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class UpdateTaskUseCaseTest {
    private val taskRepository = mock<TaskRepository>()
    private val useCase = UpdateTaskUseCase(taskRepository)

    @Test
    fun `test UpdateTask`() {
        val testTask = Task(
            name = "Test",
            description = null,
            isComplete = false
        )


        runBlocking {
            Mockito.`when`(taskRepository.updateTask(testTask.id, true)).thenReturn(Unit)

            useCase.invoke(testTask.id, true)

            // Проверяем, что метод updateTask был вызван с правильными параметрами
            verify(taskRepository).updateTask(testTask.id, true)


        }


    }
}