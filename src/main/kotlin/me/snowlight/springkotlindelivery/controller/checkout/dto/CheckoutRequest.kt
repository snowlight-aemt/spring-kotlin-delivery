package me.snowlight.springkotlindelivery.controller.checkout.dto

import me.snowlight.springkotlindelivery.controller.category.menu.dto.MenuDTO

data class CheckoutRequest (
    val customerId: Long,
    val discountId: Long,
    val storeId: Long,
)
