package com.suatzengin.infovalorant.presentation.agents.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.infovalorant.domain.use_case.agents.GetAgentByUUID
import com.suatzengin.infovalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val getAgentByUUIDUseCase: GetAgentByUUID,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(AgentDetailState())
    val state: State<AgentDetailState>
        get() = _state


    init {

        savedStateHandle.get<String>("agentUUID")?.let {
            getAgentByUUID(it)
        }
    }

    private fun getAgentByUUID(uuid: String) {
        viewModelScope.launch {
            getAgentByUUIDUseCase(uuid).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = AgentDetailState(agent = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = AgentDetailState(error = result.message ?: "Error")
                    }
                    is Resource.Loading -> {
                        _state.value = AgentDetailState(isLoading = true)
                    }
                }

            }
        }
    }
}