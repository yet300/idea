package com.ideasapp.data.wrapper

import com.ideaapp.domain.model.Task
import com.ideasapp.data.model.TaskDBO

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