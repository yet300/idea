package com.ideasapp.domain.usecase.task

import com.ideasapp.domain.model.Task
import com.ideasapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetTaskUseCaseTest {
    private val taskRepository = mock<TaskRepository>()
    private val useCase = GetTaskUseCase(taskRepository)

    @Test
    fun `test GetTask`() {

        val testTask = flowOf(
            listOf(
                Task(
                    id = 1,
                    name = "Test",
                    description = null
                ),
                Task(
                    id = 2,
                    name = "Test2",
                    description = null
                ),
                Task(
                    id = 3,
                    name = "Test3",
                    description = null
                )
            )
        )
        Mockito.`when`(taskRepository.getTasks()).thenReturn(testTask)

        val actual = useCase.invoke()

        assertEquals(actual, testTask)

    }
}