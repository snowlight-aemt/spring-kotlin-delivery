package me.snowlight.springkotlindelivery.controller

data class StoreDetailResponse (
    val storeId: Long,
    val storeName: String,
    val phone: String,
    val address: String,
    val storeMainImageUrl: String,
    val description: String,
    val menus: List<MenuDTO>
)
