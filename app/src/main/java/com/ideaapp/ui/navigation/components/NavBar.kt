package com.ideaapp.ui.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * [take from](https://github.com/Efimj/Shkiper/blob/master/app/src/main/java/com/jobik/shkiper/screens/AppLayout/NavigationBar/BottomAppBarProvider.kt#L166)
 */
@Composable
fun NavBar(
    navController: NavController,
    items: List<CustomBottomNavigationItem>,
) {
    val bottomBarRoutes = setOf(Screens.Home.rout, Screens.Task.rout)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar = navBackStackEntry?.destination?.route in bottomBarRoutes
    if (showBottomBar) {
        Surface(
            shape = CircleShape,
            shadowElevation = 1.dp,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.safeDrawingPadding()
        ) {
            Row(
                modifier = Modifier
                    .clickable(enabled = false) {}
                    .height(DefaultNavigationValues().containerHeight)
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items.forEach {
                    CustomBottomNavigationItem(properties = it)
                }
            }
        }
    }
}