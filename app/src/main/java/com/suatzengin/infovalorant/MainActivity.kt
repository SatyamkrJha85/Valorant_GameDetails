package com.suatzengin.infovalorant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.suatzengin.infovalorant.presentation.navigation.BottomBar
import com.suatzengin.infovalorant.presentation.navigation.NavigateScreens
import com.suatzengin.infovalorant.presentation.navigation.Screen
import com.suatzengin.infovalorant.ui.theme.InfoValorantTheme
import com.suatzengin.infovalorant.ui.theme.background
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfoValorantTheme {
                MainScreen()
            }
        }

    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Agents,
        Screen.Weapons,
        Screen.Maps,
        Screen.Ranks
    )
    val visible = remember { mutableStateOf(true)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = background,

        bottomBar = {
            BottomBar(screens = items, navController = navController, visible = visible.value)
        }
    ) {
        NavigateScreens(navController, it)
    }
}
