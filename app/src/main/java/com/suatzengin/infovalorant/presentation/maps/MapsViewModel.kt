package com.suatzengin.infovalorant.presentation.maps

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.infovalorant.domain.use_case.maps.GetAllMapsUseCase
import com.suatzengin.infovalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val getAllMapsUseCase: GetAllMapsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MapsState())
    val state: State<MapsState>
        get() = _state

    init {
        getAllMaps()
    }

    private fun getAllMaps() {
        viewModelScope.launch {
            getAllMapsUseCase().collect { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(maps = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message ?: "Error")
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}