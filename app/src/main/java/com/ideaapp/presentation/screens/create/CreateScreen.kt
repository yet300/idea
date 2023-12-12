package com.ideaapp.presentation.screens.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ideaapp.domain.model.Note
import com.ideaapp.presentation.ui.theme.IdeasappTheme
import com.ideaapp.presentation.ui.theme.components.Screens
import java.util.*
import com.ideaapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<CreateViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                },
                navigationIcon = {
                    TextButton(onClick = {
                        viewModel.createNote(
                            Note(
                                title = title,
                                content = description,
                                emoji = "Emoji"
                            )
                        ) {
                            navController.navigate(Screens.Home.rout)
                        }
                    }) {
                        Text(
                            text = stringResource(id = R.string.create),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            )

        },
        content = { innerpadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerpadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
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
                    Column {
                        CustomTextField(
                            value = description,
                            onValueChange = { description = it },
                            labletext = stringResource(id = R.string.note),
                            textStyle = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                        )
                    }
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

@Preview(showBackground = true)
@Composable
fun CreatePrew() {
    IdeasappTheme {
        CreateScreen(rememberNavController())
    }
}
