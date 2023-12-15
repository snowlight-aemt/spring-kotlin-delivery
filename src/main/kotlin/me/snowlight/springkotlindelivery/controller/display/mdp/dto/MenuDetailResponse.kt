package me.snowlight.springkotlindelivery.controller.display.mdp.dto

import java.math.BigDecimal

data class MenuDetailResponse (
    var menuId: Long,
    var menuName: String,
    var storeId: Long,
    var price: BigDecimal,
    var menuStatus: String,
    var description: String,
    var menuImageUrl: String,
)
