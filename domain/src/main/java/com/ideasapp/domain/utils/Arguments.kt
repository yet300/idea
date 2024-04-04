package com.ideasapp.domain.utils

sealed class Arguments<T>(
    val key: String,
    val emptyValue: T,
) {
    data object ItemId : Arguments<Long>("item_id", -1L)

    data object ReminderId : Arguments<Long>("reminder_id", -1L)

    data object Name : Arguments<String>("message", "")

    data object Description : Arguments<String>("description", "")
}