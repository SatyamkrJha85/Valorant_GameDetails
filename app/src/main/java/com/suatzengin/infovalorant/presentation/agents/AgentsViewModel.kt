package com.suatzengin.infovalorant.presentation.agents

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.infovalorant.data.repository.ValorantRepositoryImpl
import com.suatzengin.infovalorant.domain.use_case.agents.GetAllAgentsUseCase
import com.suatzengin.infovalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val getAllAgentsUseCase: GetAllAgentsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AgentsState())
    val state: State<AgentsState>
        get() = _state

    init {
        getAllAgens()
    }

    private fun getAllAgens() {
        viewModelScope.launch {
            getAllAgentsUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = AgentsState(agents = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = AgentsState(error = result.message ?: "Error")
                    }
                    is Resource.Loading -> {
                        _state.value = AgentsState(isLoading = true)
                    }
                }
            }
        }
    }

}