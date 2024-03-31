package com.ideasapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note")
data class NoteDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String? = "",
    val imageUri: String? = null,
    val isPrivate: Boolean = false
)