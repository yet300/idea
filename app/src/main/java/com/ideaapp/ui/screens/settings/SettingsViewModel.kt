package com.ideaapp.ui.screens.settings


import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    val LocalTheme = compositionLocalOf { false }


}
