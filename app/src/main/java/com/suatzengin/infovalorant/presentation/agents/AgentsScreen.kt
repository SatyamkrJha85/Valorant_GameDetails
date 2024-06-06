package com.suatzengin.infovalorant.presentation.agents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.suatzengin.infovalorant.data.remote.agents.Agents
import com.suatzengin.infovalorant.presentation.navigation.Screen
import com.suatzengin.infovalorant.ui.theme.AstraColors
import com.suatzengin.infovalorant.ui.theme.BreachColors
import com.suatzengin.infovalorant.ui.theme.ChamberColors
import com.suatzengin.infovalorant.ui.theme.CypherColors
import com.suatzengin.infovalorant.ui.theme.FadeColors
import com.suatzengin.infovalorant.ui.theme.KayoColors
import com.suatzengin.infovalorant.ui.theme.LightBlue
import com.suatzengin.infovalorant.ui.theme.LightGreen
import com.suatzengin.infovalorant.ui.theme.LightPurple
import com.suatzengin.infovalorant.ui.theme.Orange
import com.suatzengin.infovalorant.ui.theme.RazeColors
import com.suatzengin.infovalorant.ui.theme.SkyeColors
import com.suatzengin.infovalorant.ui.theme.SovaColors
import com.suatzengin.infovalorant.ui.theme.ViperColors
import com.suatzengin.infovalorant.ui.theme.background
import com.suatzengin.infovalorant.util.getColorAgentBg


@Composable
fun AgentsScreen(
    viewModel: AgentsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    val list = state.value.agents

    Scaffold(
        backgroundColor = background,
        topBar = {
            TopAppBar(
                title = {
                    Text("Agents Characters", style = MaterialTheme.typography.subtitle1)
                },
                backgroundColor = Color.Transparent,
                contentColor = Color.White,
                elevation = 0.dp
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues = it),
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = !state.value.isLoading, enter = fadeIn(initialAlpha = 0.2f)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2)
                ) {
                    items(items = list) { item ->
                        AgentListItem(item) { agent ->
                            navController.navigate(Screen.AgentDetail.route + "/${agent.uuid}")
                        }
                    }
                }
            }
            if (state.value.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            }
        }
    }
}


@Composable
fun AgentListItem(agent: Agents, onClick: (Agents) -> Unit) {

    val taskcolour = listOf(FadeColors, BreachColors, RazeColors, ChamberColors, KayoColors,SkyeColors,
        CypherColors,SovaColors,ViperColors,AstraColors  ).random()


    val taskcolour2 = listOf(LightPurple, LightBlue, LightGreen, LightBlue).random()
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clickable {
                onClick(agent)
            }
    ) {
        Card(
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
                .background(

//                    brush = Brush.verticalGradient(taskcolour2)
                    taskcolour2, shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(0.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = agent.displayName,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(top = 10.dp, start = 7.dp)
                )
                Text(
                    text = agent.role.displayName,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(4.dp),
                    fontSize = 10.sp
                )
            }
        }


        SubcomposeAsyncImage(
            model = agent.fullPortraitV2, contentDescription = "Icon Agent",
            modifier = Modifier
                //.height(240.dp)
                .size(500.dp)
                .padding(12.dp),
            loading = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            },
            alignment = Alignment.BottomEnd
        )
    }

}
