package com.ideaapp.utils

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


object ThemeChanger {
    private var _isDarkMode: MutableState<Boolean?> = mutableStateOf(null)
    var isDarkMode: MutableState<Boolean?>
        get() = _isDarkMode
        private set(value) {
            _isDarkMode = value
        }

    private var _isDynamicTheme: MutableState<Boolean?> = mutableStateOf(null)
    var isDynamicTheme: MutableState<Boolean?>
        get() = _isDynamicTheme
        private set(value) {
            _isDynamicTheme = value
        }

    fun restoreSavedTheme(context: Context) {
        val store = context.getSharedPreferences(ApplicationStorageName, Context.MODE_PRIVATE)
        isDarkMode.value = store.getBoolean(ApplicationUiModeIsDark, false)
        isDynamicTheme.value = store.getBoolean(ApplicationUiModeDynamic, false)
    }


    fun saveThemeMode(context: Context, isDarkMode: Boolean) {
        val store = context.getSharedPreferences(ApplicationStorageName, Context.MODE_PRIVATE)
        val editor = store.edit()
        editor.putBoolean(ApplicationUiModeIsDark, isDarkMode)
        editor.apply()

        this.isDarkMode.value = isDarkMode
    }

    fun saveDynamicTheme(context: Context, isDynamicTheme: Boolean) {
        val store = context.getSharedPreferences(ApplicationStorageName, Context.MODE_PRIVATE)
        val editor = store.edit()
        editor.putBoolean(ApplicationUiModeDynamic, isDynamicTheme)
        editor.apply()

        this.isDynamicTheme.value = isDynamicTheme
    }

    private const val ApplicationStorageName = "ApplicationStorageName"

    private const val ApplicationUiModeIsDark = "ApplicationUiModeIsDark"
    private const val ApplicationUiModeDynamic = "ApplicationUiModeDynamic"

}