package com.suatzengin.infovalorant.presentation.ranks


import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.suatzengin.infovalorant.data.remote.ranks.Tier
import com.suatzengin.infovalorant.ui.theme.background

@Composable
fun RanksScreen(
    viewModel: RanksViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val listOfTier = state.ranks

    Scaffold(
        backgroundColor = background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ranks",
                        style = MaterialTheme.typography.subtitle1
                    )
                },
                backgroundColor = Color.Transparent, contentColor = Color.White,
                elevation = 0.dp
            )
        },
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues = it),
            verticalArrangement = Arrangement.Center
        ){
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(items = listOfTier) { tier ->
                    RankListItem(tier = tier)
                }
            }
        }
    }

}

@Composable
fun RankListItem(tier: Tier) {
    val colorList = listOf(
        Color(parseColor("#${tier.backgroundColor.dropLast(2)}")),
        Color(parseColor("#${tier.color.dropLast(2)}"))
    )
    Card(
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .padding(12.dp)
            .height(150.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = colorList
                ), shape = RoundedCornerShape(24.dp)
            ),
        elevation = 0.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(model = tier.largeIcon, contentDescription = "Tier icon")
            Text(text = tier.tierName, style = MaterialTheme.typography.subtitle2)
        }
    }
}

