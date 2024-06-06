package com.suatzengin.infovalorant.domain.repository

import com.suatzengin.infovalorant.data.remote.Response
import com.suatzengin.infovalorant.data.remote.ResponseDetail
import com.suatzengin.infovalorant.data.remote.agents.Agents
import com.suatzengin.infovalorant.data.remote.maps.MapsDto
import com.suatzengin.infovalorant.data.remote.ranks.Ranks
import com.suatzengin.infovalorant.data.remote.weapons.Weapons


interface ValorantRepository {
    // Agents
    suspend fun getAllAgents(): Response<Agents>
    suspend fun getAgentByUUID(uuid: String): ResponseDetail<Agents>

    // Maps
    suspend fun getAllMaps(): Response<MapsDto>

    //Weapons
    suspend fun getAllWeapons(): Response<Weapons>
    suspend fun getAllWeaponByUUID(uuid: String): ResponseDetail<Weapons>

    //Ranks
    suspend fun getAllTiers(): Response<Ranks>

}