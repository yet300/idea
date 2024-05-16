package com.ideasapp.domain.utils.memento

import com.ideasapp.domain.model.Memento
import com.ideasapp.domain.model.Task

class Originator(var task: Task) {

    fun createTask(): Memento {
        return Memento(task)
    }

    fun restore(memento: Memento) {
        task = memento.task
    }
}