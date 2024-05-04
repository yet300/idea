package com.ideaapp.ui.screens.note.secure


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
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
import com.ideaapp.ui.components.custiom_bar.CollapsingTitle
import com.ideaapp.ui.components.custiom_bar.CustomTopBar
import com.ideaapp.ui.components.custiom_bar.rememberToolbarScrollBehavior
import com.ideaapp.ui.navigation.canGoBack
import com.ideaapp.ui.screens.note.secure.component.EmptyScreen
import com.ideaapp.ui.screens.note.secure.component.NoteSecureContent

@Composable
fun NoteSecureScreen(
    navController: NavHostController,
    viewModel: NoteSecureViewModel,
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()
    val scrollBehavior = rememberToolbarScrollBehavior()


    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            CustomTopBar(
                navigationIcon = {
                    BackButton(
                        onClick = {
                            if (navController.canGoBack) {


                                navController.popBackStack()
                            }
                        }
                    )
                },
                collapsingTitle = CollapsingTitle.medium(titleText = stringResource(id = R.string.secure_note)),
                scrollBehavior = scrollBehavior,
                modifier = modifier
                    .safeDrawingPadding(),

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

