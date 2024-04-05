package com.ideaapp.ui.screens.settings


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
import com.ideaapp.R
import com.ideaapp.ui.components.BackButton
import android.provider.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ideaapp.ui.navigation.canGoBack
import com.ideaapp.ui.components.TextIconComponentButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState(),
            canScroll = { true })


    val packageName = context.packageName
    val uriHandler = LocalUriHandler.current

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(title = {
                Text(
                    modifier = modifier,
                    text = stringResource(id = R.string.settings),
                    style = MaterialTheme.typography.headlineSmall,

                    )
            }, navigationIcon = {
                BackButton(
                    onClick = {
                        if (navController.canGoBack) {

                            navController.popBackStack()
                        }
                    }
                )
            },
                scrollBehavior = scrollBehavior
            )
        },
        content = {
            Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    TextIconComponentButton(
                        content = stringResource(id = R.string.language),
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
                TextIconComponentButton(
                    content = stringResource(id = R.string.telegram),
                    icon = painterResource(id = R.drawable.icons_telegram),
                    onClick = {
                        uriHandler.openUri("https://t.me/doodleGB")

                    }
                )
            }
        }
    )
}





