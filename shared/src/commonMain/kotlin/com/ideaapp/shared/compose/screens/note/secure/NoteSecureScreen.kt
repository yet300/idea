package com.ideaapp.shared.compose.screens.note.secure


//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.LargeTopAppBar
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.material3.rememberTopAppBarState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.ideaapp.shared.compose.components.BackButton
//import com.ideaapp.shared.compose.ui.screens.note.secure.component.EmptyScreen
//import com.ideaapp.shared.compose.ui.screens.note.secure.component.NoteSecureContent
//import ideasapp.shared.generated.resources.Res
//import ideasapp.shared.generated.resources.secure_note
//import org.jetbrains.compose.resources.stringResource
//import org.koin.androidx.compose.koinViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NoteSecureScreen(
//    modifier: Modifier = Modifier,
//    navController: NavHostController,
//    viewModel: NoteSecureViewModel = koinViewModel(),
//) {
//    val notes by viewModel.notes.collectAsState()
//
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
//
//    Scaffold(
//        modifier = modifier
//            .fillMaxSize()
//            .nestedScroll(scrollBehavior.nestedScrollConnection),
//        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
//        topBar = {
//            LargeTopAppBar(
//                title = {
//                    Text(
//                        text = stringResource(Res.string.secure_note),
//                        style = MaterialTheme.typography.headlineMedium
//                    )
//                },
//                navigationIcon = {
//                    BackButton(
//                        onClick = {
//                            if (navController.canNavigate()) {
//
//
//                                navController.popBackStack()
//                            }
//                        }
//                    )
//                },
//                scrollBehavior = scrollBehavior,
//
//                )
//        },
//        content = {
//            if (notes.isNotEmpty()) {
//                NoteSecureContent(paddingValues = it, navController = navController, notes = notes)
//            } else {
//                EmptyScreen()
//            }
//        },
//    )
//}

