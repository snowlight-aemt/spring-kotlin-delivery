package me.snowlight.springkotlindelivery.controller.orderhistory.dto

import me.snowlight.springkotlindelivery.domain.order.OrderStatus

data class OrderHistoryRequest (
    val customerId: Long,
    val orderStatus: OrderStatus,
)
