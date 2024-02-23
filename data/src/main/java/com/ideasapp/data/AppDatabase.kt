package com.ideasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ideasapp.data.dao.NoteDAO
import com.ideasapp.data.dao.TaskDAO

import com.ideasapp.data.model.NoteDBO
import com.ideasapp.data.model.TaskDBO


@Database(entities = [NoteDBO::class, TaskDBO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDAO
    abstract fun taskDao(): TaskDAO


}