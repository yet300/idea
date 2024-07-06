package com.ideaapp.shared.root


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.ideaapp.shared.note.create_edit.NoteCreateEditContent
import com.ideaapp.shared.tabs.TabsContent


@OptIn(ExperimentalDecomposeApi::class)
@Composable
internal fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = {
            Children(
                stack = component.childStackNavigation,
                modifier = modifier,
                animation = predictiveBackAnimation(
                    backHandler = component.backHandler,
                    fallbackAnimation = stackAnimation(fade() + scale()),
                    onBack = component::onBackClicked,
                )
            ) {
                when (val child = it.instance) {
                    is RootComponent.NavChild.NoteCreateEditChild -> NoteCreateEditContent(
                        component = child.component,
                        modifier = modifier.fillMaxSize()
                    )

                    is RootComponent.NavChild.NoteSecureChild -> TODO()

                    is RootComponent.NavChild.TabsChild -> TabsContent(
                        component = child.component,
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}