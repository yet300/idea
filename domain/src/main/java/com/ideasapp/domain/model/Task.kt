package com.ideasapp.domain.model

 data class Task(
    val id: Long = 0,
    val name: String,
    val description: String?,
    val isComplete: Boolean = false
)
