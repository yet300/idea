package com.ideasapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ideasapp.domain.model.Note

@Entity("note")
data class NoteDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String? = "",
    val imageUri: String? = null,
    val isPrivate: Boolean = false
)


// В вашем модуле data
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