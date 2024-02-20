package com.ideaapp.ui.screens.note.secure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.ui.navigation.canGoBack
import com.ideaapp.ui.navigation.components.Screens
import com.ideaapp.ui.screens.note.main.components.NoteItem
import com.ideaapp.ui.components.BackButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecureScreen(
    navController: NavHostController,
    viewModel: SecureViewModel,
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            rememberTopAppBarState(),
            canScroll = { true })

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
                        style = MaterialTheme.typography.headlineSmall,
                    )
                },
                navigationIcon = {
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
        content = { contentPadding ->
            Box(
                modifier = modifier
                    .padding(contentPadding)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                if (notes.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = modifier
                            .fillMaxWidth(),
                        columns = GridCells.Fixed(2),
                    ) {
                        items(
                            notes,
                            key = { note -> note.id }) { note ->
                            NoteItem(
                                title = note.title,
                                image = note.imageUri,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp, horizontal = 5.dp)
                                    .clickable { navController.navigate(Screens.Details.rout + "/${note.id}") }
                            )
                        }
                        // Добавляем пустые элементы в конец списка
                        repeat(20) {
                            item {
                                Spacer(modifier = modifier.height(15.dp)) // Выберите высоту, которая вам подходит
                            }
                        }

                    }
                } else {
                    SecureEmpty()
                }
            }
        },
    )
}
