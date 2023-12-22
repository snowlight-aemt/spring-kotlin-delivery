package me.snowlight.springkotlindelivery.service.cart

import io.swagger.v3.oas.annotations.media.Schema

data class AddCartItem(
    val storeId: Long,
    val menuId: Long,
    val quantity: Int,
    val customerId: Long,
)
