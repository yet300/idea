package com.ideaapp.ui.screens.note.main.components


import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.components.mToast
import com.ideaapp.ui.screens.note.secure.AndroidBiometricAuthenticator
import com.ideaapp.ui.screens.note.secure.AuthenticationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    navController: NavHostController,
    query: MutableState<String>,
    context: Context,
    modifier: Modifier = Modifier,
) {
    val biometricAuthenticator = AndroidBiometricAuthenticator(LocalContext.current)
    biometricAuthenticator.setOnAuthListener { result ->
        // Обработка результата аутентификации
        when (result) {
            is AuthenticationResult.Success -> {
                navController.navigate(Screens.Secure.rout)
            }

            is AuthenticationResult.Failed -> {
                // Аутентификация не удалась, выполните необходимые действия
                mToast(context, context.getString(R.string.faild))
            }

            is AuthenticationResult.Error -> {
                // Произошла ошибка в процессе аутентификации, выполните необходимые действия
                mToast(context, context.getString(R.string.error))
            }
        }
    }


    var expended by remember {
        mutableStateOf(false)
    }



    SearchBar(
        query = query.value,
        onQueryChange = {
            query.value = it
        },
        onSearch = {
            query.value = it
        },
        active = false,
        onActiveChange = {},

        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                expended = true
            }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert, contentDescription = null
                )
            }
            DropdownMenu(
                expanded = expended,
                onDismissRequest = { expended = false },
                offset = DpOffset(x = 10.dp, y = 8.dp),
                modifier = modifier
                    .padding(6.dp)
                    .clip(MaterialTheme.shapes.small)

            ) {
                DropdownMenuItem(text = {
                    Text(
                        text = stringResource(id = R.string.secure_note),
                        style = MaterialTheme.typography.titleMedium
                    )
                }, onClick = {
                    biometricAuthenticator.authenticate(context)

                }, trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = stringResource(id = R.string.secure_note)
                    )
                })

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )


                DropdownMenuItem(text = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.titleMedium
                    )
                }, onClick = {
                    navController.navigate(Screens.Settings.rout)
                }, trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                })
            }

        },

        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
    ) {

    }
}


