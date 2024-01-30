package com.ideaapp.presentation.navigation.components

sealed class Screens(val rout: String) {
    data object Home : Screens(rout = "main_screen")
    data object Task : Screens(rout = "task_screen")



    data object Details : Screens(rout = "details_screen")
    data object Create : Screens(rout = "create_screen")

    data object Secure : Screens(rout = "secure_screen")

    data object CreateTask : Screens(rout = "create_task_screen")

}