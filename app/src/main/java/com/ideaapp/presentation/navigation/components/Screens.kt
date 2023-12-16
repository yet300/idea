package com.ideaapp.presentation.navigation.components

sealed class Screens(val rout: String) {
    data object Home : Screens(rout = "main_screen")
    data object Search : Screens(rout = "search_screen")

    data object Details : Screens(rout = "details_screen")
    data object Create : Screens(rout = "create_screen")
}