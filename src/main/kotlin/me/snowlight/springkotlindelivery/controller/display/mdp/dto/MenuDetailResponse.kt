package me.snowlight.springkotlindelivery.controller.display.mdp.dto

import me.snowlight.springkotlindelivery.domain.catalog.menu.MenuStatus
import java.math.BigDecimal

data class MenuDetailResponse (
    var menuId: Long,
    var menuName: String,
    var storeId: Long,
    var price: BigDecimal,
    var menuStatus: MenuStatus,
    var description: String,
    var menuMainImageUrl: String,
)
