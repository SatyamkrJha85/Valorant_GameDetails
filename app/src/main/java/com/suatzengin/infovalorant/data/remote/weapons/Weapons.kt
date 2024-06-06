package com.suatzengin.infovalorant.data.remote.weapons

data class Weapons(
    val uuid: String,
    val displayName: String,
    val category: String,
    val defaultSkinUuid: String,
    val displayIcon: String,
    val killStreamIcon: String,
    val assetPath: String,
    val weaponStats: WeaponStats?,
    val shopData: ShopData?,
    val skins: List<Skin>,

)
