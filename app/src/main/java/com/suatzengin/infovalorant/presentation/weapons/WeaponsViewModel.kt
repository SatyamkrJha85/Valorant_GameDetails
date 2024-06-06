package com.suatzengin.infovalorant.presentation.weapons

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.infovalorant.domain.use_case.weapons.GetAllWeapons
import com.suatzengin.infovalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val getAllWeaponsUseCase: GetAllWeapons
) : ViewModel() {

    private val _state = mutableStateOf(WeaponsState())
    val state: State<WeaponsState>
        get() = _state

    init {
        getAllWeapons()
    }

    private fun getAllWeapons() {
        getAllWeaponsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = WeaponsState(weapons = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = WeaponsState(error = result.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = WeaponsState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}