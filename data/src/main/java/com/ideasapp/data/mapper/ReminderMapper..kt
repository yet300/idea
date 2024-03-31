package com.ideasapp.data.mapper

import com.ideasapp.data.model.ReminderDBO
import com.ideasapp.domain.model.Reminder

fun ReminderDBO.toDomainReminder(): Reminder {
    return Reminder(
        id = id,
        itemId = itemId,
        reminderTime = reminderTime,
        name = name,
        description = description ?: "",
    )
}

fun Reminder.toRoomReminder(): ReminderDBO {
    return ReminderDBO(
        id = id,
        itemId = itemId,
        reminderTime = reminderTime,
        name = name,
        description = description,
    )
}