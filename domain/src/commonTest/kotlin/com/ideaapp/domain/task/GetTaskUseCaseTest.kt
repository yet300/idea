package com.ideaapp.domain.task

//import com.ideaapp.domain.model.Task
//import com.ideaapp.domain.repository.TaskRepository
//import com.ideaapp.domain.usecase.task.GetTaskUseCase
//import io.mockative.Mockable
//import io.mockative.mock
//import kotlinx.coroutines.flow.flowOf
//import kotlin.test.Test
//import kotlin.test.assertEquals
//
//class GetTaskUseCaseTest {
//    private val taskRepository = mock<TaskRepository>()
//    private val useCase = GetTaskUseCase(taskRepository)
//
//    @Test
//    fun `test GetTask`() {
//
//        val testTask = flowOf(
//            listOf(
//                Task(
//                    id = 1,
//                    name = "Test",
//                    description = null
//                ),
//                Task(
//                    id = 2,
//                    name = "Test2",
//                    description = null
//                ),
//                Task(
//                    id = 3,
//                    name = "Test3",
//                    description = null
//                )
//            )
//        )
//        Mockable.`when`(taskRepository.getTasks()).thenReturn(testTask)
//        val actual = useCase.invoke()
//
//        assertEquals(actual, testTask)
//
//    }
//}