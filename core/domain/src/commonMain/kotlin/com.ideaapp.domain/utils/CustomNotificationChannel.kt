package com.ideaapp.domain.utils

enum class CustomNotificationChannel(
    val id: String,
    val title: String,
    val importance: Int
) {
    TASKS(
        id = "task_channel",
        title = "Tasks",
        importance = 3,
    ),

    //TODO add later
    NOTES(
        id = "note_channel",
        title = "Notes",
        importance = 3,
    );

    companion object {
        fun asList(): List<CustomNotificationChannel> {
            return listOf(
                TASKS
            )
        }
    }
}


