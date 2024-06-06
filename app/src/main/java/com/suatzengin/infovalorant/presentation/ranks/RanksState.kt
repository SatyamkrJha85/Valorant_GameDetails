package com.suatzengin.infovalorant.presentation.ranks

import com.suatzengin.infovalorant.data.remote.ranks.Tier

data class RanksState(
    val ranks: List<Tier> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
