package com.ideaapp.presentation.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ideaapp.domain.model.Note
import com.ideaapp.presentation.ui.theme.IdeasappTheme
import com.ideaapp.presentation.ui.theme.components.Screens
import java.util.*
import com.ideaapp.R


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
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .height(48.dp)
                    .padding(horizontal = 24.dp)

            ) {
                Box(
                    modifier = modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(MaterialTheme.colorScheme.onBackground)
                        .clickable {
                            val color: Int = Color(
                                Random().nextInt(256),
                                Random().nextInt(256),
                                Random().nextInt(256),
                            ).toArgb()

                            viewModel.createNote(
                                Note(
                                    title = title,
                                    content = description,
                                    backgroundColor = color,
                                    emoji = "Emoji"
                                )
                            ) {
                                navController.navigate(Screens.Home.rout)
                            }
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "nav_add",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                }

            }
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
                        modifier = modifier.fillMaxWidth()
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
                                .fillMaxWidth()
                                .height(400.dp)
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
    )
}

@Preview(showBackground = true)
@Composable
fun CreatePrew() {
    IdeasappTheme {
        CreateScreen(rememberNavController())
    }
}
