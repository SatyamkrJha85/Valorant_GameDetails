package com.suatzengin.infovalorant.presentation.agents

import com.suatzengin.infovalorant.data.remote.agents.Agents

data class AgentsState(
    val agents: List<Agents> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
