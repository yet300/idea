package com.ideaapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val imageUri: String? = null
//    @ColumnInfo(name = "created_at") val createdAt: Date = Date(System.currentTimeMillis()),
//    @ColumnInfo(name = "updated_at") val updatedAt: Date = Date(System.currentTimeMillis()),
)
