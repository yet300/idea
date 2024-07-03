package com.ideasapp.data.repository

import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.TaskRepository
import com.ideasapp.data.dao.TaskDAO
import com.ideasapp.data.wrapper.toDomainTask
import com.ideasapp.data.wrapper.toRoomNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val dao: TaskDAO) : TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks().map { list ->
            list.map { it.toDomainTask() }
        }
    }

    override suspend fun getTaskById(id: Long): Task? {
        return dao.getTaskById(id)?.toDomainTask()
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task.toRoomNote())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toRoomNote())
    }

    override suspend fun updateTask(id: Long, complete: Boolean) {
        dao.updateTask(id, complete)
    }

}