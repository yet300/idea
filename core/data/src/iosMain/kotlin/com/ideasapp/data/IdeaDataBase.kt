package com.ideasapp.data

//import androidx.room.Room
//import platform.Foundation.NSHomeDirectory

//fun IdeaDataBase(): IdeaDataBase {
//    val dbFile = NSHomeDirectory.absolutePath + "/idea_database"
//    val database = Room.databaseBuilder<AppDatabase>(
//        name = dbFile,
//        factory = { AppDatabase::class.instantiateImpl() }
//    ).setDriver(BundledSQLiteDriver()).build()
//    return IdeaDataBase(database)
//}