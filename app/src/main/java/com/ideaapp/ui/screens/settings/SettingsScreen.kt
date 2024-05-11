package com.ideaapp.ui.screens.settings


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ideaapp.R
import com.ideaapp.ui.components.IconComponentButton
import com.ideaapp.ui.components.IconComponentSwitcher
import com.ideaapp.utils.ThemeChanger


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    context: Context,
    modifier: Modifier = Modifier
) {
    val packageName = context.packageName
    val uriHandler = LocalUriHandler.current

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = modifier,
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.headlineSmall,
                        )
                },
            )
        },
        content = {
            Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                IconComponentSwitcher(
                    text = stringResource(id = R.string.theme),
                    checked = ThemeChanger.isDarkMode.value ?: false,
                    onCheckedChange = { viewModel.themeChange() },
                    icon = if (ThemeChanger.isDarkMode.value == true) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    IconComponentSwitcher(
                        text = stringResource(id = R.string.dynamic_color),
                        checked = ThemeChanger.isDynamicTheme.value ?: true,
                        onCheckedChange = { viewModel.dynamicThemeChange() },
                        icon = Icons.Default.ColorLens
                    )
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    IconComponentButton(
                        content = {
                            Text(
                                text = stringResource(id = R.string.language),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        },
                        icon = Icons.Default.Language,
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Settings.ACTION_APP_LOCALE_SETTINGS,
                                    Uri.parse("package:$packageName")
                                )
                            )
                        }
                    )
                }
                IconComponentButton(
                    content = {
                        Text(
                            text = stringResource(id = R.string.telegram),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    icon = painterResource(id = R.drawable.icons_telegram),
                    onClick = {
                        uriHandler.openUri("https://t.me/doodleGB")

                    }
                )
            }
        }
    )
}





