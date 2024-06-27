package com.ideaapp.shared.compose.screens.settings


//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ColorLens
//import androidx.compose.material.icons.filled.Language
//import androidx.compose.material.icons.outlined.DarkMode
//import androidx.compose.material.icons.outlined.LightMode
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalUriHandler
//import com.ideaapp.shared.compose.components.IconComponentButton
//import com.ideaapp.shared.compose.components.IconComponentSwitcher
//import com.ideaapp.shared.compose.ui.screens.SettingsViewModel
//import com.ideaapp.utils.ThemeChanger
//import ideasapp.shared.generated.resources.Res
//import ideasapp.shared.generated.resources.dynamic_color
//import ideasapp.shared.generated.resources.icons_telegram
//import ideasapp.shared.generated.resources.language
//import ideasapp.shared.generated.resources.settings
//import ideasapp.shared.generated.resources.telegram
//import ideasapp.shared.generated.resources.theme
//import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.compose.resources.stringResource
//import org.koin.androidx.compose.koinViewModel
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SettingsScreen(
//    modifier: Modifier = Modifier,
//    viewModel: SettingsViewModel = koinViewModel(),
//    context: Context,
//) {
//    val packageName = context.packageName
//    val uriHandler = LocalUriHandler.current
//
//    Scaffold(
//        modifier = modifier
//            .fillMaxSize(),
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(
//                        modifier = modifier,
//                        text = stringResource(Res.string.settings),
//                        style = MaterialTheme.typography.headlineSmall,
//                    )
//                },
//            )
//        },
//        content = {
//            Column(
//                modifier = modifier
//                    .padding(it)
//                    .fillMaxSize()
//            ) {
//                IconComponentSwitcher(
//                    text = stringResource(Res.string.theme),
//                    checked = ThemeChanger.isDarkMode.value ?: false,
//                    onCheckedChange = { viewModel.themeChange() },
//                    icon = if (ThemeChanger.isDarkMode.value == true) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
//                )
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                    IconComponentSwitcher(
//                        text = stringResource(Res.string.dynamic_color),
//                        checked = ThemeChanger.isDynamicTheme.value ?: true,
//                        onCheckedChange = { viewModel.dynamicThemeChange() },
//                        icon = Icons.Default.ColorLens
//                    )
//                }
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    IconComponentButton(
//                        content = {
//                            Text(
//                                text = stringResource(Res.string.language),
//                                style = MaterialTheme.typography.titleMedium,
//                            )
//                        },
//                        icon = Icons.Default.Language,
//                        onClick = {
//                            context.startActivity(
//                                Intent(
//                                    Settings.ACTION_APP_LOCALE_SETTINGS,
//                                    Uri.parse("package:$packageName")
//                                )
//                            )
//                        }
//                    )
//                }
//                IconComponentButton(
//                    content = {
//                        Text(
//                            text = stringResource(Res.string.telegram),
//                            style = MaterialTheme.typography.titleMedium,
//                        )
//                    },
//                    icon = painterResource(Res.drawable.icons_telegram),
//                    onClick = {
//                        uriHandler.openUri("https://t.me/doodleGB")
//
//                    }
//                )
//            }
//        }
//    )
//}





