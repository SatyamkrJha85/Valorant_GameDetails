package com.suatzengin.infovalorant.presentation.weapons.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.infovalorant.data.remote.weapons.Skin
import com.suatzengin.infovalorant.data.remote.weapons.Weapons
import com.suatzengin.infovalorant.domain.use_case.weapons.GetWeaponByUUID
import com.suatzengin.infovalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponDetailViewModel @Inject constructor(
    private val getWeaponByUUIDUseCase: GetWeaponByUUID,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(WeaponDetailState())
    val state: State<WeaponDetailState>
        get() = _state

    init {
        savedStateHandle.get<String>("weaponUUID")?.let {
            getWeaponByUUID(it)
        }
    }

    private fun getWeaponByUUID(uuid: String) {
        viewModelScope.launch {
            getWeaponByUUIDUseCase(uuid).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = WeaponDetailState(weapon = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = WeaponDetailState(error = result.message ?: "Error")
                    }
                    is Resource.Loading -> {
                        _state.value = WeaponDetailState(isLoading = true)
                    }
                }

            }
        }
    }

    fun randomSkins(weapon: Weapons): List<Skin> {
        val skins = weapon.skins.toMutableList()

        val randomSkins = arrayListOf<Skin>()
        repeat(5) {
            randomSkins.add(skins.random())
        }
        return randomSkins
    }
}