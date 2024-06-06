package com.suatzengin.infovalorant.data.remote.weapons

data class WeaponStats(
    val fireRate: Double,
    val magazineSize: Int,
    val runSpeedMultiplier: Double,
    val equipTimeSeconds: Double,
    val reloadTimeSeconds: Double,
    val firstBulletAccuracy: Double,
    val shotgunPelletCount: Int,
    val wallPenetration: String,
    val feature: String,
    val fireMode: String?,
    val altFireType: String,
    val damageRanges: List<DamageRange>
)
