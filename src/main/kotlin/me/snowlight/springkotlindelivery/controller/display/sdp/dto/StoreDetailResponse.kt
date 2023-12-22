package me.snowlight.springkotlindelivery.controller.display.sdp.dto

import me.snowlight.springkotlindelivery.controller.category.menu.dto.MenuDTO
import java.math.BigDecimal

data class StoreDetailResponse (
    val storeId: Long,
    val storeName: String,
    val phone: String,
    val address: String,
    val deliveryFee: BigDecimal,
    val deliveryTime: String,
    val minimumOrderPrice: BigDecimal,
    val reviewGrade: Int,
    val storeMainImageUrl: String,
    val description: String,
    val menus: List<MenuDTO>
)
