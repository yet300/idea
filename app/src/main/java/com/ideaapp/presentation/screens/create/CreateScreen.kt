package com.ideaapp.presentation.screens.create

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ideaapp.domain.model.Note
import com.ideaapp.presentation.ui.theme.components.Screens
import com.ideaapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    navController: NavController,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            rememberTopAppBarState(),
            canScroll = { true })

    val viewModel = hiltViewModel<CreateViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }



    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                },
                actions = {
                    Text(
                        text = stringResource(id = R.string.create),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = modifier
                            .clickable {
                                if (title.isNotEmpty()) {
                                    viewModel.createNote(
                                        Note(
                                            title = title,
                                            content = description,
                                            emoji = "Emoji"
                                        )
                                    ) {
                                        navController.navigate(Screens.Home.rout)
                                    }
                                }
                            }
                            .padding(6.dp)
                    )
                },
                scrollBehavior = scrollBehavior,
                modifier = modifier
            )


        },
        content = { innerpadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerpadding)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                state = listState
            ) {
                item {
                    CustomTextField(
                        value = title, onValueChange = {
                            title = it
                            isTextFieldFocused = it.isNotEmpty()
                        },
                        labletext = stringResource(
                            id = R.string.title
                        ),
                        textStyle = MaterialTheme.typography.headlineSmall,
                        modifier = modifier
                    )
                }
                item {
                    CustomTextField(
                        value = description,
                        onValueChange = { description = it },
                        labletext = stringResource(id = R.string.note),
                        textStyle = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                    )
                }
            }
        })
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labletext: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
        ),
        label = {
            Text(
                text = labletext,
                style = textStyle,
            )
        },
        modifier = modifier
            .fillMaxWidth()
    )
}

