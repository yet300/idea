package com.ideaapp.domain.utils.memento

import com.ideaapp.domain.model.Memento
import com.ideaapp.domain.model.Task

class Originator(var task: Task) {

    fun createTask(): Memento {
        return Memento(task)
    }

    fun restore(memento: Memento) {
        task = memento.task
    }
}