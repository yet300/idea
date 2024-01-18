package com.ideaapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ideaapp.domain.model.Note
import com.ideaapp.domain.repository.NoteRepository


@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteRepository

}