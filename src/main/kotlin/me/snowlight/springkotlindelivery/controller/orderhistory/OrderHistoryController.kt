package me.snowlight.springkotlindelivery.controller.orderhistory

import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryRequest
import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryDTO
import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryResponse
import me.snowlight.springkotlindelivery.service.orderhistory.OrderHistoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
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
}