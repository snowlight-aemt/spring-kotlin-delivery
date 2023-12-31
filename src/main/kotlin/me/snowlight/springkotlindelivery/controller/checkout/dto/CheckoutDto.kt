package me.snowlight.springkotlindelivery.controller.checkout.dto

data class CheckoutDto (
    val checkoutItemId: Long,
    val menuId: Long,
    val menuPrice: Long,
    val menuQuantity: Long,
)