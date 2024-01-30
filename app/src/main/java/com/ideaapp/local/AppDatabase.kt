package com.ideaapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.model.Task
import com.ideaapp.domain.repository.NoteRepository
import com.ideaapp.domain.repository.TaskRepository


@Database(entities = [Note::class, Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteRepository
    abstract fun taskDao(): TaskRepository
}