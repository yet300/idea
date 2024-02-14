package com.ideaapp.presentation.screens.settings


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ideaapp.R
import com.ideaapp.presentation.ui.theme.components.BackButton
import android.provider.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import com.ideaapp.presentation.screens.settings.components.SettingComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    context: Context,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState(),
            canScroll = { true })


    val packageName = context.packageName


    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(title = {
                Text(
                    modifier = modifier,
                    text = stringResource(id = R.string.settings),
                )
            }, navigationIcon = {
                BackButton(
                    onClick = { navController.popBackStack() }
                )
            },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Created by Ruslan Gadzhiev",
                    style = MaterialTheme.typography.titleSmall
                )

            }
        },
        content = {
            Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    SettingComponent(
                        content = "Language",
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
//                    SwitchSettings(
//                        content = "Dynamic theme",
//                        icon = if (viewModel.isDynamicThemeEnabled.value) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
//                        isChecked = viewModel.isDynamicThemeEnabled.value,
//                        onClick = {
//                            viewModel.setDynamicThemeEnabled(!viewModel.isDynamicThemeEnabled.value)
//                        }
//                    )

            }
        }
    )
}





