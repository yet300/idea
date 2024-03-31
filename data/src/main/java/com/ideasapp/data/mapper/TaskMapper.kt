package com.ideasapp.data.mapper

import com.ideasapp.data.model.TaskDBO
import com.ideasapp.domain.model.Task

fun TaskDBO.toDomainTask(): Task {
    return Task(
        id = this.id,
        name = this.name,
        description = this.description,
        reminderTime = this.reminderTime,
        isComplete = this.isComplete
    )
}

fun Task.toRoomNote(): TaskDBO {
    return TaskDBO(
        id = this.id,
        name = this.name,
        description = this.description,
        reminderTime = this.reminderTime,
        isComplete = this.isComplete
    )
}