package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.model.Task
import com.ideasapp.domain.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class DeleteTaskUseCaseTest {
    private val taskRepository = mock<TaskRepository>()
    private val useCase = DeleteTaskUseCase(taskRepository)

    @Test
    fun `test DeleteTask`() {
        val testTask = Task(
            id = 1,
            name = "Test",
            description = null
        )

        runBlocking {
            Mockito.`when`(taskRepository.deleteTask(testTask)).thenReturn(Unit)
            val actual = useCase.invoke(testTask)
            assertEquals(actual, Unit)
        }
    }
}