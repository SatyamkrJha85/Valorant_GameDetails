package com.suatzengin.infovalorant.presentation.maps

import com.suatzengin.infovalorant.data.remote.maps.MapsDto


data class MapsState(
    val maps: List<MapsDto> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
