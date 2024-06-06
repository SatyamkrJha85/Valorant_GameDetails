package com.suatzengin.infovalorant.presentation.agents.detail

import com.suatzengin.infovalorant.data.remote.agents.Agents

data class AgentDetailState(
    val agent: Agents? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
