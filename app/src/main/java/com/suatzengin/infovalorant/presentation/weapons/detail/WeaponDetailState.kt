package com.suatzengin.infovalorant.presentation.weapons.detail

import com.suatzengin.infovalorant.data.remote.weapons.Weapons

data class WeaponDetailState(
    val weapon: Weapons? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
