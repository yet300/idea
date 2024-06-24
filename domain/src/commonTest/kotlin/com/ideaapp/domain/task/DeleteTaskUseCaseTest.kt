package com.ideaapp.domain.task

//import com.ideaapp.domain.model.Task
//import com.ideaapp.domain.repository.ReminderRepository
//import com.ideaapp.domain.repository.TaskRepository
//import com.ideaapp.domain.usecase.task.DeleteTaskUseCase
//import io.mockative.Mock
//import io.mockative.Mockable
//import io.mockative.classOf
//import io.mockative.mock
//import kotlinx.coroutines.runBlocking
//import kotlin.test.Test
//import kotlin.test.assertEquals
//
//class DeleteTaskUseCaseTest {
//    private val taskRepository = mock<TaskRepository>(classOf())
//    private val reminderRepository = mock<ReminderRepository>(classOf())
//    private val useCase = DeleteTaskUseCase(
//        taskRepository,
//        reminderRepository
//    )
//
//    @Test
//    fun `test DeleteTask`() {
//        val testTask = Task(
//            id = 1,
//            name = "Test",
//            description = null
//        )
//
//        runBlocking {
//            Mockable.`when`(taskRepository.deleteTask(testTask)).thenReturn(Unit)
//            val actual = useCase.invoke(testTask)
//            assertEquals(actual, Unit)
//        }
//    }
//}