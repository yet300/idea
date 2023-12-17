package com.ideaapp.presentation.screens.create

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ideaapp.domain.model.Note
import com.ideaapp.R
import com.ideaapp.presentation.navigation.components.Screens
import com.ideaapp.presentation.screens.create.components.CustomTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {


    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)
    val rememberedScrollBehavior = remember { scrollBehavior }

    val keyboardHeight = remember { mutableStateOf(0.dp) }

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
                scrollBehavior = rememberedScrollBehavior,
                modifier = modifier
            )


        },
        content = { innerpadding ->
            Box(modifier = modifier.padding(innerpadding)) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = keyboardHeight.value),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    CustomTextField(
                        value = title,
                        onValueChange = {
                            title = it
                            isTextFieldFocused = it.isNotEmpty()
                        },
                        labletext = stringResource(
                            id = R.string.title
                        ),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = modifier
                    )
                    CustomTextField(
                        value = description,
                        onValueChange = { description = it },
                        labletext = stringResource(id = R.string.note),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                    )

                }
            }
        },
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        )
}



