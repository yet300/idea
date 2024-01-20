package com.ideaapp.presentation.screens.secure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.ui.theme.components.NoteItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecureScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<SecureViewModel>()
    val notes = viewModel.notes.observeAsState(listOf()).value



    Scaffold(
        modifier = modifier
            .fillMaxSize(),

        topBar = {
            TopAppBar(
                modifier = modifier,
                title = {
                    Text(
                        text = stringResource(id = R.string.secure_note),
                        style = MaterialTheme.typography.bodyLarge,
                        )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = null
                        )
                    }
                },
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
                            .fillMaxWidth()
                            .padding(8.dp),
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
                    }
                } else {
                    SecureEmpty()
                }
            }
        },
    )
}

