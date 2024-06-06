package com.suatzengin.infovalorant.domain.use_case.ranks

import com.suatzengin.infovalorant.data.remote.ranks.Tier
import com.suatzengin.infovalorant.domain.repository.ValorantRepository
import com.suatzengin.infovalorant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetAllTiersUseCase @Inject constructor(
    private val repository: ValorantRepository
) {

    operator fun invoke(): Flow<Resource<List<Tier>>> = flow {
        emit(Resource.Loading())
        try {
            val lastEpisodeRanks = repository.getAllTiers().data.last().tiers.filter {
                it.tierName !in listOf("UNRANKED", "Unused1", "Unused2")
            }
            emit(Resource.Success(lastEpisodeRanks))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Network Error!"))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }
    }

}