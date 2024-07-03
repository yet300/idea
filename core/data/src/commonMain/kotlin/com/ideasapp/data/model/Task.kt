package com.ideasapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("task")
data class TaskDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String?,
    val reminderTime: Long? = null,
    val isComplete: Boolean = false
)





