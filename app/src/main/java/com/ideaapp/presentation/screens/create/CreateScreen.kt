package com.ideaapp.presentation.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ideaapp.domain.model.Note
import com.ideaapp.presentation.ui.theme.IdeasappTheme
import com.ideaapp.presentation.ui.theme.components.Screens
import java.util.*

@Composable
fun CreateScreen(
    navController: NavController,
) {

    val viewModel = hiltViewModel<CreateViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .height(48.dp)
                    .padding(horizontal = 24.dp)

            ) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFF383838))
                        .clickable { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "nav_back",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                }

                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFF383838))
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
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                }

            }
        }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.padding(top = 24.dp)
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun CreatePrew() {
    IdeasappTheme {
        CreateScreen(rememberNavController())
    }
}