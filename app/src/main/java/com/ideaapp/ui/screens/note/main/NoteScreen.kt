package com.ideaapp.ui.screens.note.main


import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.components.FAB
import com.ideaapp.ui.components.TrailingItem
import com.ideaapp.ui.components.custiom_bar.CollapsingTitle
import com.ideaapp.ui.components.custiom_bar.CustomSearchTopBar
import com.ideaapp.ui.components.custiom_bar.rememberToolbarScrollBehavior
import com.ideaapp.ui.screens.note.main.components.EmptyScreen
import com.ideaapp.ui.screens.note.main.components.NoteContent
import com.ideaapp.ui.screens.note.main.components.Search
import com.ideaapp.utils.BiometricPromptManager


@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel,
    activity: AppCompatActivity,
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()

    val searchText by viewModel.searchText.collectAsState()
    val queryNotes = if (searchText.isEmpty()) {
        notes.filter {
            !it.isPrivate
        }
    } else {
        notes.filter {
            !it.isPrivate
            it.title.lowercase().contains(searchText.lowercase())
        }
    }
    val scrollBehavior = rememberToolbarScrollBehavior()
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
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize()
            .safeDrawingPadding(),
        floatingActionButton = {
            FAB(
                onClick = { navController.navigate(Screens.NoteCreateEdit.rout) },
                modifier.safeDrawingPadding()
            )
        },
        topBar = {
            CustomSearchTopBar(
                actions = {
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
                collapsingTitle = CollapsingTitle.large(titleText = stringResource(id = R.string.app_name)),
                additionalContent = {
                    Search(
                        query = searchText,
                        onQueryChange = {
                            viewModel.setSearchText(it)
                        },
                        onSearchChange = {
                            viewModel.performSearch()
                        },
                        modifier = modifier
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
        content = {
            if (queryNotes.isNotEmpty()) {
                NoteContent(
                    navController = navController,
                    paddingValues = it,
                    queryNotes = queryNotes,
                )
            } else {
                EmptyScreen()
            }
        }
    )
}