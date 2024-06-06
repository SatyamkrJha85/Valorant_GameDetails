package com.suatzengin.infovalorant.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.suatzengin.infovalorant.ui.theme.bottomBackground
import com.suatzengin.infovalorant.ui.theme.selectedBottomItemColor


@Composable
fun BottomBar(
    screens: List<Screen>,
    navController: NavHostController,
    visible: Boolean
) {

    BottomNavigation(
        backgroundColor = bottomBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            AnimatedVisibility(visible = visible) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.image!!),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    label = {
                        Text(
                            text = screen.route.uppercase(),
                            style = MaterialTheme.typography.subtitle1,
                            fontSize = 10.sp
                        )
                    },
                    selectedContentColor = selectedBottomItemColor,
                    unselectedContentColor = Color.White,
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    alwaysShowLabel = false
                )
            }

        }
    }
}
