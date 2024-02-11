package com.ideaapp.presentation.screens.note.main.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun NoteItem(title: String, image: String?, modifier: Modifier = Modifier) {
    OutlinedCard(
        border = CardDefaults.outlinedCardBorder().copy(0.dp),
        modifier = modifier
            .size(width = 240.dp, height = 200.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {

        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = Uri.parse(image))
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = title,
                modifier = Modifier
                    .padding(10.dp),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )
        }
    }
}
