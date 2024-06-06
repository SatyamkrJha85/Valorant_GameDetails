package com.suatzengin.infovalorant.presentation.ranks


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.infovalorant.domain.use_case.ranks.GetAllTiersUseCase
import com.suatzengin.infovalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RanksViewModel @Inject constructor(
    private val getAllTiersUseCase: GetAllTiersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RanksState())
    val state: State<RanksState>
        get() = _state

    init {
        getAllTiers()
    }

    private fun getAllTiers() {
        getAllTiersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(ranks = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?: "Error!")
                }
            }

        }.launchIn(viewModelScope)
    }

}