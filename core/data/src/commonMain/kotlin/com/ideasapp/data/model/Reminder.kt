package com.ideasapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("reminder")
data class ReminderDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val itemId: Long,
    val reminderTime: Long,
    val name: String,
    val description: String? = "",
)




