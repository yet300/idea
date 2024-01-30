package com.ideaapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val isCompleted: Boolean = false
)




