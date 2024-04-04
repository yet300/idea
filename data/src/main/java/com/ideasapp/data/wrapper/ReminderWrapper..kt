package com.ideasapp.data.wrapper

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