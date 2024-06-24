package com.ideaapp.domain.utils.memento

import com.ideaapp.domain.model.Memento


class Caretaker {
    private val mementoList = mutableListOf<Memento>()
    fun saveTask(originator: Originator) {
        mementoList.add(originator.createTask())
    }

    fun undo(originator: Originator) {
        if (mementoList.isNotEmpty()) {
            val memento = mementoList.removeAt(mementoList.size - 1)
            originator.restore(memento)
        }
    }
}