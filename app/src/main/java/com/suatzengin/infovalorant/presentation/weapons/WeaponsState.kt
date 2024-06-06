package com.suatzengin.infovalorant.presentation.weapons

import com.suatzengin.infovalorant.data.remote.weapons.Weapons

data class WeaponsState(
    val weapons: List<Weapons> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
