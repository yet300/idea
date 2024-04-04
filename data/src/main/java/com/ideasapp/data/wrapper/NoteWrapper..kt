package com.ideasapp.data.wrapper

import com.ideasapp.data.model.NoteDBO
import com.ideasapp.domain.model.Note

fun NoteDBO.toDomainNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        content = this.content ?: "",
        imageUri = this.imageUri ?: "",
        isPrivate = this.isPrivate
    )
}

fun Note.toRoomNote(): NoteDBO {
    return NoteDBO(
        id = this.id,
        title = this.title,
        content = this.content,
        imageUri = this.imageUri,
        isPrivate = this.isPrivate
    )
}