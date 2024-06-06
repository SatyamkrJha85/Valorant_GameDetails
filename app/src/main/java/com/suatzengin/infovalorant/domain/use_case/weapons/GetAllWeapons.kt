package com.suatzengin.infovalorant.domain.use_case.weapons

import com.suatzengin.infovalorant.data.remote.weapons.Weapons
import com.suatzengin.infovalorant.domain.repository.ValorantRepository
import com.suatzengin.infovalorant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetAllWeapons @Inject constructor(
    private val repository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Weapons>>> = flow {
        emit(Resource.Loading())
        try {
            val weapons = repository.getAllWeapons().data
            emit(Resource.Success(data = weapons))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Network Error!"))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }
    }
}