package com.ideasapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ideasapp.domain.model.Task


@Entity("task")
data class TaskDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String?,
    val isComplete: Boolean = false
)


fun TaskDBO.toDomainTask(): Task {
    return Task(
        id = this.id,
        name = this.name,
        description = this.description,
        isComplete = this.isComplete
    )
}

fun Task.toRoomNote(): TaskDBO {
    return TaskDBO(
        id = this.id,
        name = this.name,
        description = this.description,
        isComplete = this.isComplete
    )
}


