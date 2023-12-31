package me.snowlight.springkotlindelivery.controller.order.dto

import me.snowlight.springkotlindelivery.domain.order.OrderStatus
import me.snowlight.springkotlindelivery.repository.orderItem.OrderItem
import java.math.BigDecimal

data class OrderCreateResponse (
    val orderId: Long,
    val orderUUID: String,
    val orderShortenId: String,
    val checkoutId: Long,
    val orderAmount: BigDecimal,
    val discountAmount: BigDecimal,
    val deliveryFee: BigDecimal,
    val totalAmount: BigDecimal,
    val orderStatus: OrderStatus = OrderStatus.READY,
    val storeId: Long,
    val customerId: Long,
)