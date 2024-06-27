package com.ideasapp.data.wrapper

import com.ideaapp.domain.model.Note
import com.ideasapp.data.model.NoteDBO

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