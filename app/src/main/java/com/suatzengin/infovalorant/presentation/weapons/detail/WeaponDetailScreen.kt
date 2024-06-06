package com.suatzengin.infovalorant.presentation.weapons.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.suatzengin.infovalorant.data.remote.weapons.Skin
import com.suatzengin.infovalorant.data.remote.weapons.Weapons
import com.suatzengin.infovalorant.ui.theme.background


@Composable
fun WeaponDetailScreen(
    viewModel: WeaponDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .background(color = background)

    ) {
        state.weapon?.let { weapon ->

            DisplayWeapon(weapon = weapon)

            WeaponStats(weapon = weapon)

            WeaponDamageRanges(weapon = weapon)

            Text(
                text = "Random 5 Skins", color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )

            val randomSkinList = viewModel.randomSkins(weapon = weapon)

            LazyRow {
                items(items = randomSkinList) { skin ->
                    Skins(skin, weapon.displayIcon)
                }
            }
        }
    }

}


@Composable
fun AnnotatedStr(value: Double?) {
    val buildAnnotatedStr = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            ) {
                append("$value / ")
                withStyle(
                    style = SpanStyle(color = Color.LightGray, fontWeight = FontWeight.Light)
                ) {
                    append("Sec")
                }
            }
        }
    }
    Text(text = buildAnnotatedStr)
}

@Composable
fun DisplayWeapon(weapon: Weapons) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = weapon.displayIcon,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
        )

        Text(
            text = weapon.displayName, fontSize = 32.sp, color = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}

@Composable
fun WeaponStats(weapon: Weapons) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(),

        ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .padding(9.dp),
        ) {
            Column {
                Text(text = "Kill Stream Icon", color = Color.White, fontWeight = FontWeight.Bold)
                AsyncImage(model = weapon.killStreamIcon, contentDescription = "Kill stream icon")
            }
            Column {
                Text(text = "Category", color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = "${weapon.shopData?.category}", color = Color.White)
            }
            Column {
                Text(text = "Cost", color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = "${weapon.shopData?.cost}", color = Color.White)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .padding(9.dp),
        ) {
            Column {
                Text(text = "Reload Speed", color = Color.White, fontWeight = FontWeight.Bold)
                AnnotatedStr(value = weapon.weaponStats?.reloadTimeSeconds)
            }
            Column {
                Text(text = "Equip Speed", color = Color.White, fontWeight = FontWeight.Bold)
                AnnotatedStr(value = weapon.weaponStats?.equipTimeSeconds)
            }
        }
    }
}

@Composable
fun WeaponDamageRanges(weapon: Weapons) {
    if (weapon.weaponStats != null) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(weapon.weaponStats.damageRanges.size),
        ) {
            items(items = weapon.weaponStats.damageRanges) { range ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .background(color = Color.DarkGray, shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "${range.rangeStartMeters.toInt()}m-${range.rangeEndMeters.toInt()}m",
                        textDecoration = TextDecoration.Underline, color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Head: ${range.headDamage.toInt()}", color = Color.White)
                    Text(text = "Body: ${range.bodyDamage.toInt()}", color = Color.White)
                    Text(text = "Leg: ${range.legDamage.toInt()}", color = Color.White)
                }
            }
        }
    }


}

@Composable
fun Skins(skin: Skin, defaultSkin: String) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .size(200.dp)
            .padding(12.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(Color.White, Color.LightGray, Color(0xFFC7D6D0), Color.Gray)
                ), shape = RoundedCornerShape(12.dp)
            ),
        backgroundColor = Color.Transparent,
    ) {
        AsyncImage(
            model = skin.displayIcon ?: defaultSkin,
            contentDescription = "skin icon"
        )
    }
}

