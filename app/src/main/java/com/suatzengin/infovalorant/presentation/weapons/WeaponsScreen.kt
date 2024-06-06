package com.suatzengin.infovalorant.presentation.weapons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.suatzengin.infovalorant.data.remote.weapons.Weapons
import com.suatzengin.infovalorant.presentation.navigation.Screen
import com.suatzengin.infovalorant.ui.theme.background

@Composable
fun WeaponsScreen(
    viewModel: WeaponsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    Scaffold(
        backgroundColor = background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Weapons",
                        style = MaterialTheme.typography.subtitle1
                    )
                },
                backgroundColor = Color.Transparent, contentColor = Color.White,
                elevation = 0.dp
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(paddingValues = it)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.weapons) { weapon ->
                    WeaponsItem(weapon = weapon) {
                        navController.navigate(Screen.WeaponDetail.route + "/${weapon.uuid}")
                    }
                }
            }
        }
    }


}

@Composable
fun WeaponsItem(
    weapon: Weapons,
    onClickItem: (Weapons) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp,
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
        modifier = Modifier
            .padding(16.dp)
            .size(150.dp)
            .clickable {
                onClickItem(weapon)
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(4.dp)
        ) {
            SubcomposeAsyncImage(
                model = weapon.displayIcon, contentDescription = "weapon icon",
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = weapon.displayName,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 24.sp
            )
            Text(text = "${weapon.shopData?.category}")
        }

    }
}