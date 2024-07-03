package com.ideasapp.data

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun IdeaDataBase(applicationContext: Context): IdeaDataBase {
    val dbFile = applicationContext.getDatabasePath("idea_database")
    val database = Room.databaseBuilder<AppDatabase>(
        applicationContext.applicationContext,
        dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver()).build()
    return IdeaDataBase(database)
}