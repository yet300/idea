package com.ideasapp.domain.model

data class Note(
    val id: Long = 0,
    val title: String = "",
    val content: String? = "",
    val imageUri: String? = null,
    val isPrivate: Boolean = false
)