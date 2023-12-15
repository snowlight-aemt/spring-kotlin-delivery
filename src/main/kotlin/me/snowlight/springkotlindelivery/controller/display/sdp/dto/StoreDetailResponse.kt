package me.snowlight.springkotlindelivery.controller.display.sdp.dto

import me.snowlight.springkotlindelivery.controller.category.menu.dto.MenuDTO

data class StoreDetailResponse (
    val storeId: Long,
    val storeName: String,
    val phone: String,
    val address: String,
    val storeMainImageUrl: String,
    val description: String,
    val menus: List<MenuDTO>
)
