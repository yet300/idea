package com.ideaapp.ui.navigation.components

sealed class Screens(val rout: String) {

    /*
    Note Screens
     */
    object Home : Screens(rout = "main_screen")
    object Details : Screens(rout = "details_screen")
    object Create : Screens(rout = "create_screen")
    object Secure : Screens(rout = "secure_screen")

    object Settings : Screens(rout = "settings_screen")

    /*
    Task Screens
     */
    object Task : Screens(rout = "task_screen")

    object TaskDetail : Screens(rout = "task_detail_screen")

}