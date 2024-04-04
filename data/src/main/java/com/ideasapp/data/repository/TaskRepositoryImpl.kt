package com.ideasapp.data.repository

import com.ideasapp.data.dao.TaskDAO
import com.ideasapp.data.wrapper.toDomainTask
import com.ideasapp.data.wrapper.toRoomNote
import com.ideasapp.domain.model.Task
import com.ideasapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDAO) : TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks().map { list ->
            list.map { it.toDomainTask() }
        }
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