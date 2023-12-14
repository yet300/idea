package com.ideaapp.presentation.screens.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ideaapp.presentation.ui.theme.components.Screens


@Composable
fun DetailsScreen(
    navController: NavController,
    id: String?,
) {

    val viewModel = hiltViewModel<DetailsViewModel>()

    val note = viewModel.note.observeAsState().value


    id?.toLong()?.let { viewModel.getNoteById(id = it) }
    Log.d("checkData", "id: $id")
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
                            viewModel.deleteNote {
                                navController.navigate(Screens.Home.rout)

                            }
                        }

                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "delete",
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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = note?.title ?: "",
                fontSize = 35.sp,
                style = TextStyle(Color.Unspecified, fontWeight = FontWeight.Light)
            )
            Text(
                text = note?.content ?: "",
                fontSize = 23.sp,
                style = TextStyle(
                    Color.Unspecified, fontWeight = FontWeight.Light
                )
            )

        }


    }

}