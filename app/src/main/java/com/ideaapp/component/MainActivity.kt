package com.ideaapp.component

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.ideaapp.shared.App
import com.ideaapp.shared.root.DefaultRootComponent
import com.ideaapp.ui.theme.IdeasAppTheme
import com.ideaapp.utils.ThemeChanger

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            )

        )
        super.onCreate(savedInstanceState)
        ThemeChanger.restoreSavedTheme(this)
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
        )

        setContent {
            IdeasAppTheme(
                darkTheme = ThemeChanger.isDarkMode.value ?: isSystemInDarkTheme(),
                dynamicColor = ThemeChanger.isDynamicTheme.value ?: true
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    SetupNavHost(
//                        context = LocalContext.current,
//
//                        )
                    App(component = root)
                }
            }
        }
    }
}

