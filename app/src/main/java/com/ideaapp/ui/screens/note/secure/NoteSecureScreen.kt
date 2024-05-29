package com.ideaapp.ui.screens.note.secure


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.ui.components.BackButton
import com.ideaapp.ui.navigation.components.NavController.Companion.canNavigate
import com.ideaapp.ui.screens.note.secure.component.EmptyScreen
import com.ideaapp.ui.screens.note.secure.component.NoteSecureContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteSecureScreen(
    navController: NavHostController,
    viewModel: NoteSecureViewModel,
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.secure_note),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    BackButton(
                        onClick = {
                            if (navController.canNavigate()) {


                                navController.popBackStack()
                            }
                        }
                    )
                },
                scrollBehavior = scrollBehavior,

                )
        },
        content = {
            if (notes.isNotEmpty()) {
                NoteSecureContent(paddingValues = it, navController = navController, notes = notes)
            } else {
                EmptyScreen()
            }
        },
    )
}

