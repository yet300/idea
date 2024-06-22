package com.ideaapp.ui.navigation.components

import com.ideaapp.shared.compose.ui.navigation.components.Screens

sealed class Screens(val rout: String) {
    /*
    Note Screens
     */
    object Note : Screens(rout = "main_screen")

    object NoteCreateEdit : Screens(rout = "create_edit_screen")

    object Secure : Screens(rout = "secure_screen")

    /*
    Task Screens
     */
    object Task : Screens(rout = "task_screen")

    object TaskCreateEdit : Screens(rout = "task_detail_screen")


    /*
    Task Screen
     */
    object Settings : Screens(rout = "settings_screen")


}