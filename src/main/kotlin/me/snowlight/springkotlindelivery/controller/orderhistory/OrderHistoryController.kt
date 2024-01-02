package me.snowlight.springkotlindelivery.controller.orderhistory

import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryRequest
import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryDTO
import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryResponse
import me.snowlight.springkotlindelivery.service.orderhistory.OrderHistoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderHistoryController(
    private val orderHistoryService: OrderHistoryService,
) {
    @GetMapping("/apis/order-history")
    fun list(@ModelAttribute request: OrderHistoryRequest): OrderHistoryResponse {
        val orderHistories = orderHistoryService.list(request)

        return OrderHistoryResponse(
            orderHistories = orderHistories.map { OrderHistoryDTO.from(it) }
        )
    }

    @GetMapping("/apis/order-history/{orderId}")
    fun detail(@PathVariable orderId: Long): OrderHistoryDTO {
        val orderHistory = orderHistoryService.detail(orderId)

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