package com.ideaapp.domain.model

data class Task(
    val id: Long = 0,
    val name: String = "",
    val description: String? = "",
    val reminderTime: Long? = null,
    val isComplete: Boolean = false
)
