package com.ideaapp.presentation.screens.settings


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ideaapp.R
import com.ideaapp.presentation.ui.theme.components.BackButton
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.material3.Surface


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    context: Context,
    modifier: Modifier = Modifier
) {
    var isDarkTheme by remember {
        mutableStateOf(false)
    }
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
        content = {
            Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    LanguageSettingComponent(
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
                SwitchSettings(
                    content = "Dark theme",
                    icon = if (isDarkTheme) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
                    isChecked = isDarkTheme,
                    onClick = {
                        isDarkTheme = !isDarkTheme
                    }
                )
            }
        }
    )
}


@Composable
fun SwitchSettings(
    modifier: Modifier = Modifier,
    content: String,
    icon: ImageVector,
    isChecked: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(imageVector = icon, contentDescription = null)

        Spacer(modifier = modifier.padding(8.dp))

        Column(
            modifier = modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Switch(
            checked = isChecked,
            onCheckedChange = { onClick() }
        )
    }
}

@Composable
fun LanguageSettingComponent(
    modifier: Modifier = Modifier,
    content: String,
    icon: ImageVector,
    onClick: (() -> Unit) = {},
) {
    Surface(
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = null)

            Spacer(modifier = modifier.padding(8.dp))

            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }

}


