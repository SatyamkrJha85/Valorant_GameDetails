package com.suatzengin.infovalorant.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suatzengin.infovalorant.presentation.agents.AgentsScreen
import com.suatzengin.infovalorant.presentation.agents.detail.AgentDetailScreen
import com.suatzengin.infovalorant.presentation.maps.MapsScreen
import com.suatzengin.infovalorant.presentation.ranks.RanksScreen
import com.suatzengin.infovalorant.presentation.weapons.WeaponsScreen
import com.suatzengin.infovalorant.presentation.weapons.detail.WeaponDetailScreen


@Composable
fun NavigateScreens(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = Screen.Agents.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(Screen.Agents.route) {
            AgentsScreen(navController = navController)
        }
        composable(Screen.Weapons.route) {
            WeaponsScreen(navController = navController)
        }
        composable(Screen.Maps.route) {
            MapsScreen()
        }
        composable(Screen.Ranks.route) {
            RanksScreen()
        }
        composable(
            route = Screen.AgentDetail.route + "/{agentUUID}"
        ) {
            AgentDetailScreen()
        }
        composable(route = Screen.WeaponDetail.route + "/{weaponUUID}"){
            WeaponDetailScreen()
        }

    }
}
