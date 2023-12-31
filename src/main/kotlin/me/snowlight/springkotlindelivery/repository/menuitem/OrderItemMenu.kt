package me.snowlight.springkotlindelivery.repository.menuitem

import java.math.BigDecimal

data class OrderItemMenu(
    val orderId: Long,
    val orderItemId: Long,
    val menuId: Long,
    val menuName: String,
    val menuPrice: BigDecimal,
    val menuMainImageUrl: String,
)