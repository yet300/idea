package com.ideasapp.domain.model

data class Reminder(
    val id: Long? = null,
    val itemId: Long,
    val reminderTime: Long,
    val name: String,
    val description: String,
)