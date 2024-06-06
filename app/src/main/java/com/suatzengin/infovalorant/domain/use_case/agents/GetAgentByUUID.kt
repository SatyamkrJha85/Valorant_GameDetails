package com.suatzengin.infovalorant.domain.use_case.agents

import com.suatzengin.infovalorant.data.remote.agents.Agents
import com.suatzengin.infovalorant.domain.repository.ValorantRepository
import com.suatzengin.infovalorant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetAgentByUUID @Inject constructor(
    private val repository: ValorantRepository
) {

    operator fun invoke(uuid: String): Flow<Resource<Agents>> = flow {
        emit(Resource.Loading())
        try {
            val agent = repository.getAgentByUUID(uuid).data
            emit(Resource.Success(data = agent))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Network Error!"))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }
    }
}