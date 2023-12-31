package me.snowlight.springkotlindelivery.controller.checkout.dto

data class CheckoutListResponse (
    val customerId: Long,
    val storeId: Long,
    val totalAmountForPayment: Long,
    val checkoutItems: List<CheckoutDto>
)