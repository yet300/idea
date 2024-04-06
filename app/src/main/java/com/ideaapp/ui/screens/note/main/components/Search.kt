package com.ideaapp.ui.screens.note.main.components


import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.utils.BiometricPromptManager
import android.provider.Settings
import android.widget.Toast
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    navController: NavHostController,
    query: MutableState<String>,
    activity: AppCompatActivity,
    modifier: Modifier = Modifier,
) {

    val showToastMessage: (String) -> Unit = { message ->
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
    val promptManager = remember { BiometricPromptManager(activity) }


    val biometricResult by promptManager.promptResults.collectAsState(
        initial = null
    )

    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            navController.navigate(Screens.Secure.rout)
        }
    )
    LaunchedEffect(biometricResult) {
        biometricResult?.let { result ->
            when (result) {
                is BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                    navController.navigate(Screens.Secure.rout)
                }

                is BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                    if (Build.VERSION.SDK_INT >= 30) {
                        // Запуск активности установки пароля при отсутствии аутентификации
                        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                            putExtra(
                                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                            )
                        }
                        enrollLauncher.launch(enrollIntent)
                    } else {
                        showToastMessage(activity.getString(R.string.auth_not_set))
                    }
                }

                is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                    showToastMessage(result.error)
                }

                BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                    showToastMessage(activity.getString(R.string.auth_faild))
                }

                BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                    showToastMessage(activity.getString(R.string.auth_unavailable))
                }

                BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                    showToastMessage(activity.getString(R.string.auth_hardware_unavailable))
                }
            }
        }
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
            var showBottomSheet by remember { mutableStateOf(false) }

            IconButton(onClick = {
                showBottomSheet = true
            }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert, contentDescription = null
                )
            }

            TrailingItem(
                showBottomSheet = showBottomSheet,
                onDismiss = { showBottomSheet = false },
                secureOnClick = {
                    promptManager.showBiometricPrompt(
                        title = activity.getString(R.string.app_name),
                    )
                },
                settingsOnClick = { navController.navigate(Screens.Settings.rout) }
            )
        },

        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
    ) {

    }
}


