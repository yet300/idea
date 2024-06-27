package com.ideaapp.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.ideaapp.shared.root.RootComponent
import com.ideaapp.shared.root.RootScreen
import com.ideaapp.shared.theme.IdeasAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    setSingletonImageLoaderFactory {
        getImageLoader(it)
    }

    IdeasAppTheme(content = {
        Scaffold(
            content = { paddingFromPrent ->
                Column(
                    Modifier
                        .padding(paddingFromPrent)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RootScreen(component, modifier)
                }
            }
        )
    })
}

fun getImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()

