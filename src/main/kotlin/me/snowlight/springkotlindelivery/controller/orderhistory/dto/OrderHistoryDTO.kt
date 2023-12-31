package me.snowlight.springkotlindelivery.controller.orderhistory.dto

import me.snowlight.springkotlindelivery.domain.order.OrderStatus
import me.snowlight.springkotlindelivery.service.orderhistory.dto.OrderHistory
import java.math.BigDecimal

data class OrderHistoryDTO(
    val orderId: Long,
    val orderStatus: OrderStatus,
    val storeId: Long,
    val storeName: String,
    val menuCount: Int,
    val menuNames: List<String>,
    val menuRepresentativeImageUrl: String,
    val totalOrderAmount: BigDecimal,
) {
    companion object {
        fun from(orderHistory: OrderHistory): OrderHistoryDTO {
            return OrderHistoryDTO(
                orderId = orderHistory.orderId,
                orderStatus = orderHistory.orderStatus,
                storeId = orderHistory.storeId,
                storeName = orderHistory.storeName,
                menuCount = orderHistory.menuCount,
                menuNames = orderHistory.menuNames,
                menuRepresentativeImageUrl = orderHistory.menuRepresentativeImageUrl,
                totalOrderAmount = orderHistory.totalOrderAmount,
            )
        }
    }
}
