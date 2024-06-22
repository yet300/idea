package com.ideaapp.shared.compose.ui.screens.settings


import android.app.Application
import androidx.lifecycle.ViewModel
import com.ideaapp.utils.ThemeChanger

class SettingsViewModel(
    private val application: Application,
) : ViewModel() {

    fun themeChange() {
        ThemeChanger.saveThemeMode(
            context = application.applicationContext,
            isDarkMode = !(ThemeChanger.isDarkMode.value ?: false),
        )
    }

    fun dynamicThemeChange() {
        ThemeChanger.saveDynamicTheme(
            context = application.applicationContext,
            isDynamicTheme = !(ThemeChanger.isDynamicTheme.value ?: false)
        )
    }
}
