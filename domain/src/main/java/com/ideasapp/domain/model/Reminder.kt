package com.ideasapp.domain.model

data class Reminder(
    val id: Long? = 0,
    val itemId: Long = 0,
    val reminderTime: Long = 0,
    val name: String = "",
    val description: String = "",
)