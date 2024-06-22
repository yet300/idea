package com.ideaapp.shared.compose.ui.screens.note.create_edit

sealed class NoteCreateEditUiEvent {
    data class UpdateTitle(val title: String) : NoteCreateEditUiEvent()

    data class UpdateContent(val content: String) : NoteCreateEditUiEvent()

    data class UpdateUrl(val url: String) : NoteCreateEditUiEvent()

    data class UpdatePrivate(val private: Boolean) : NoteCreateEditUiEvent()

    data object Delete : NoteCreateEditUiEvent()

    data object Save : NoteCreateEditUiEvent()
}