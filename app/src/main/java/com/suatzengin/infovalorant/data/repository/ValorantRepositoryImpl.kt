package com.suatzengin.infovalorant.data.repository

import com.suatzengin.infovalorant.data.remote.Response
import com.suatzengin.infovalorant.data.remote.ResponseDetail
import com.suatzengin.infovalorant.data.remote.ValorantApiService
import com.suatzengin.infovalorant.data.remote.agents.Agents
import com.suatzengin.infovalorant.data.remote.maps.MapsDto
import com.suatzengin.infovalorant.data.remote.ranks.Ranks
import com.suatzengin.infovalorant.data.remote.weapons.Weapons
import com.suatzengin.infovalorant.domain.repository.ValorantRepository
import javax.inject.Inject

class ValorantRepositoryImpl @Inject constructor(
    private val apiService: ValorantApiService
) : ValorantRepository {

    override suspend fun getAllAgents(): Response<Agents> = apiService.getAllAgents()
    override suspend fun getAgentByUUID(uuid: String): ResponseDetail<Agents> =
        apiService.getAgentByUUID(uuid)


    override suspend fun getAllMaps(): Response<MapsDto> = apiService.getAllMaps()


    override suspend fun getAllWeapons(): Response<Weapons> = apiService.getAllWeapons()
    override suspend fun getAllWeaponByUUID(uuid: String): ResponseDetail<Weapons> {
        return apiService.getWeaponByUUID(uuid)
    }

    override suspend fun getAllTiers(): Response<Ranks> = apiService.getAllTiers()

}
