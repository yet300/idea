package com.ideaapp.ui.components

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ideaapp.R
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.screens.note.main.components.Search
import com.ideaapp.utils.BiometricPromptManager

@Composable
fun SearchBar(
    isVisible: Boolean,
    value: String,
    onChange: (newValue: String) -> Unit,
    onSearchChange: (newValue: String) -> Unit,
    navController: NavController,
    activity: AppCompatActivity,
    modifier: Modifier = Modifier
) {
    val isFocused = remember { mutableStateOf(false) }
    val startPaddings by animateDpAsState(
        if (isFocused.value) 0.dp else startWindowInsetsPadding(),
        label = "horizontalPaddings"
    )
    val endPaddings by animateDpAsState(
        if (isFocused.value) 0.dp else endWindowInsetsPadding(),
        label = "horizontalPaddings"
    )
    var showBottomSheet by remember { mutableStateOf(false) }

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
                                BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
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
    AnimatedVisibility(
        visible = isVisible || isFocused.value,
        enter = slideInVertically { -it },
        exit = slideOutVertically { -it },
    ) {
        Row(
            modifier = Modifier
                .padding(start = startPaddings, end = endPaddings),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Search(
                query = value,
                onQueryChange = onChange,
                onSearchChange = onSearchChange,
                onClick = {
                    showBottomSheet = true
                },
                modifier = modifier,
            )

            TrailingItem(
                showBottomSheet = showBottomSheet,
                onDismiss = { showBottomSheet = false },
                secureOnClick = {
                    promptManager.showBiometricPrompt(
                        title = activity.getString(R.string.app_name),
                    )
                },
            )
        }
    }
}