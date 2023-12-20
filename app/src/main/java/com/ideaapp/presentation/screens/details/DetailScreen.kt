package com.ideaapp.presentation.screens.details


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    id: String?,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<DetailsViewModel>()

    val note = viewModel.note.observeAsState().value


    val keyboardHeight = remember { mutableStateOf(0.dp) }


    //appBar Scrolling
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)
    val rememberedScrollBehavior = remember { scrollBehavior }


    id?.toLong()?.let { viewModel.getNoteById(id = it) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = null
                        )
                    }
                },
                actions = {
                    Text(
                        text = stringResource(id = R.string.delete),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = modifier
                            .clickable {
                                viewModel.deleteNote {
                                    navController.navigate(Screens.Home.rout)

                                }

                            }
                            .padding(6.dp)
                    )
                },
                scrollBehavior = rememberedScrollBehavior,
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

                )
        },
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = keyboardHeight.value)
            ) {
                Text(
                    text = note?.title ?: "",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                )
                Text(
                    text = note?.content ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                )
//
//                TextField(
//                    value = note?.title ?: "",
//                    onValueChange = {
//                        viewModel.titleText.value = it
//                        viewModel.
//                    },
//                    textStyle = TextStyle(
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = MaterialTheme.colorScheme.onSurface
//                    ),
//                    singleLine = false,
//                    readOnly = !isEditingState,
//                    enabled = isEditingState,
//                    colors = TextFieldDefaults.colors(
//                        disabledTextColor = MaterialTheme.colorScheme.surface,
//                        focusedContainerColor = MaterialTheme.colorScheme.surface,
//                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
//                        disabledContainerColor = MaterialTheme.colorScheme.surface,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent,
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .semantics { contentDescription = titleTextField },
//                    placeholder = {
//                        Text(
//                            text = titleInput,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = MaterialTheme.colorScheme.onSurface
//                        )
//                    }
//                )
//                TextField(
//                    value = viewModel.descriptionText.value,
//                    onValueChange = {
//                        viewModel.descriptionText.value = it
//                    },
//                    textStyle = TextStyle(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Normal,
//                        color = MaterialTheme.colorScheme.onSurface
//                    ),
//                    singleLine = false,
//                    readOnly = !isEditingState,
//                    enabled = isEditingState,
//                    shape = RectangleShape,
//                    colors = TextFieldDefaults.colors(
//                        disabledTextColor = MaterialTheme.colorScheme.surface,
//                        focusedContainerColor = MaterialTheme.colorScheme.surface,
//                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
//                        disabledContainerColor = MaterialTheme.colorScheme.surface,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent,
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight()
//                        .semantics { contentDescription = bodyTextField },
//                    placeholder = {
//                        Text(
//                            text = bodyInput,
//                            fontSize = 18.sp,
//                            color = MaterialTheme.colorScheme.onSurface
//                        )
//                    },
//                )
            }
        }

    }

}