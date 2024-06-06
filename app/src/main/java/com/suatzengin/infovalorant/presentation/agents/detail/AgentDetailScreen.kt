package com.suatzengin.infovalorant.presentation.agents.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.suatzengin.infovalorant.data.remote.agents.Agents

@Composable
fun AgentDetailScreen(
    viewModel: AgentDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        state.agent?.let { agent ->
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                AsyncImage(
                    model = agent.background,
                    contentDescription = "Agent Image", alpha = 0.2f
                )
                AsyncImage(
                    model = agent.fullPortraitV2,
                    contentDescription = "Agent Image"
                )
                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        text = agent.displayName, style = MaterialTheme.typography.subtitle1,
                        color = Color.White, fontSize = 32.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
                    )
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = agent.role.displayIcon, contentDescription = "role icon",
                            modifier = Modifier
                                .size(16.dp)
                        )
                        Text(
                            text = agent.role.displayName,
                            style = MaterialTheme.typography.subtitle1,
                            color = Color.White, fontSize = 16.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Text(
                text = agent.description, color = Color.White,
                modifier = Modifier.padding(12.dp)
            )

            Abilities(agent)
        }
    }

}

@Composable
fun Abilities(agents: Agents) {
    val selectedIndex = remember {
        mutableStateOf(0)
    }
    Text(
        text = "ABILITES", style = MaterialTheme.typography.subtitle1, color = Color.White,
        fontSize = 24.sp, textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(12.dp))
    val abilities = agents.abilities

    Column {

        ScrollableTabRow(
            selectedTabIndex = selectedIndex.value,
            backgroundColor = Color.Transparent,
            edgePadding = 12.dp,

            ) {
            abilities.forEachIndexed { index, ability ->
                Tab(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .background(
                            color = if (selectedIndex.value == index) Color.Red else Color.Transparent
                        )
                        .padding(16.dp),
                    selected = selectedIndex.value == index,
                    onClick = { selectedIndex.value = index },
                ) {
                    AsyncImage(
                        model = ability.displayIcon, contentDescription = "",
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(12.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.LightGray)


        ) {
            Text(
                text = abilities[selectedIndex.value].description,
                color = Color.Black,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}
