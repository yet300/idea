package com.ideaapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
//    @ColumnInfo(name = "created_at") val createdAt: Date = Date(System.currentTimeMillis()),
//    @ColumnInfo(name = "updated_at") val updatedAt: Date = Date(System.currentTimeMillis()),


    val emoji: String
)
