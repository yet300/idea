package com.ideasapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ideasapp.data.dao.NoteDAO
import com.ideasapp.data.dao.TaskDAO

import com.ideasapp.data.model.NoteDBO
import com.ideasapp.data.model.TaskDBO

class IdeaDataBase internal constructor(private val database: AppDatabase) {
    val noteDao: NoteDAO
        get() = database.noteDao()

    val taskDao: TaskDAO
        get() = database.taskDao()

}


@Database(entities = [NoteDBO::class, TaskDBO::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDAO
    abstract fun taskDao(): TaskDAO

}

fun IdeaDataBase(applicationContext: Context): IdeaDataBase {
    val database = Room.databaseBuilder(
        applicationContext.applicationContext,
        AppDatabase::class.java,
        "notes_database"
    ).build()

    return IdeaDataBase(database)
}
