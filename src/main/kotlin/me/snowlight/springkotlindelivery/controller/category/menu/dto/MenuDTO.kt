package me.snowlight.springkotlindelivery.controller.category.menu.dto

import me.snowlight.springkotlindelivery.repository.menu.Menu
import java.math.BigDecimal


data class MenuDTO (
    val menuId: Long,
    val menuName: String,
    val storeId: Long,
    val price: BigDecimal,
    val description: String,
    val menuImageUrl: String,
) {
    constructor(menu: Menu) : this(
        menuId = menu.id,
        menuName = menu.menuName,
        storeId = menu.store.id,
        price = menu.price,
        description = menu.description,
        menuImageUrl = menu.menuImageUrl,
    )
}