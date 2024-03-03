package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.model.Task
import com.ideasapp.domain.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CreateTaskUseCaseTest {
    private val taskRepository = mock<TaskRepository>()
    private val useCase = CreateTaskUseCase(taskRepository)

    @Test
    fun `test CreateTask`() {
        val testTask = Task(
            id = 0,
            name = "Test0",
            description = null
        )

        runBlocking {
            useCase.invoke(testTask)

            verify(taskRepository).insertTask(testTask)
        }
    }

}