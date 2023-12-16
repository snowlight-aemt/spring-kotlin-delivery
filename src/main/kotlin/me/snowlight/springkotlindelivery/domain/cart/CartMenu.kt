package me.snowlight.springkotlindelivery.domain.cart

import java.math.BigDecimal

// TODO feat 데이터베이스 타입과 다름
data class CartMenu(
    val cartId: Long,
    val cartItemId: Long,
    val menuId: Long,
    val menuName: String,
    val menuImageUrl: String,
    val price: BigDecimal,
    val quantity: Int,
)