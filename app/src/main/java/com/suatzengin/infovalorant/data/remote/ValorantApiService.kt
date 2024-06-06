package com.suatzengin.infovalorant.data.remote

import com.suatzengin.infovalorant.data.remote.agents.Agents
import com.suatzengin.infovalorant.data.remote.maps.MapsDto
import com.suatzengin.infovalorant.data.remote.ranks.Ranks
import com.suatzengin.infovalorant.data.remote.weapons.Weapons
import com.suatzengin.infovalorant.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApiService {

    //Agents
    @GET(Constants.AGENTS)
    suspend fun getAllAgents(): Response<Agents>

    @GET(Constants.AGENTS + "/{uuid}")
    suspend fun getAgentByUUID(@Path("uuid") uuid: String): ResponseDetail<Agents>

    //Maps
    @GET(Constants.MAPS)
    suspend fun getAllMaps(): Response<MapsDto>

    //Weapons
    @GET(Constants.WEAPONS)
    suspend fun getAllWeapons(): Response<Weapons>

    @GET(Constants.WEAPONS + "/{uuid}")
    suspend fun getWeaponByUUID(@Path("uuid") uuid: String): ResponseDetail<Weapons>

    // Ranks
    @GET(Constants.TIERS)
    suspend fun getAllTiers(): Response<Ranks>
}