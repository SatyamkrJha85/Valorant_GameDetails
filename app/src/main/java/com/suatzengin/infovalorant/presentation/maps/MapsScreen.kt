package com.suatzengin.infovalorant.presentation.maps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.suatzengin.infovalorant.data.remote.maps.MapsDto
import com.suatzengin.infovalorant.ui.theme.background

@Composable
fun MapsScreen(
    viewModel: MapsViewModel = hiltViewModel()
) {
    val stateMap = viewModel.state
    val list = stateMap.value.maps

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Maps",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                },
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            )
        },
        backgroundColor = background
    ) {

        Column(
            modifier = Modifier.padding(paddingValues = it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            LazyColumn {
                items(items = list) { map ->
                    MapsCardItem(map = map)
                }
            }
        }
    }
}



@Composable
fun MapsCardItem(map: MapsDto) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(150.dp),
    ) {
        Box {
            SubcomposeAsyncImage(
                model = map.splash,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                loading = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            )
            Text(
                text = map.displayName,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
    }
}










