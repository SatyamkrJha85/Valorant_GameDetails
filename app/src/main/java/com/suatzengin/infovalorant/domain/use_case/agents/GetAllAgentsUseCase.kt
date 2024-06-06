package com.suatzengin.infovalorant.domain.use_case.agents

import com.suatzengin.infovalorant.data.remote.agents.Agents
import com.suatzengin.infovalorant.domain.repository.ValorantRepository
import com.suatzengin.infovalorant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


class GetAllAgentsUseCase @Inject constructor(
    private val repository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Agents>>> = flow {
        emit(Resource.Loading())
        try {

            val agents = repository.getAllAgents().data.filter {
                it.isPlayableCharacter
            }
            println("${agents[0].displayName} dsa")
            emit(Resource.Success(data = agents))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Network Error!"))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }
    }
}