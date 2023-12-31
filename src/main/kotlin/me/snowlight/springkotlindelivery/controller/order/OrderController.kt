package me.snowlight.springkotlindelivery.controller.order

import me.snowlight.springkotlindelivery.controller.order.dto.OrderCreateRequest
import me.snowlight.springkotlindelivery.controller.order.dto.OrderCreateResponse
import me.snowlight.springkotlindelivery.service.order.OrderService
import me.snowlight.springkotlindelivery.service.orderhistory.OrderHistoryService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("/apis/orders")
    fun order(request: OrderCreateRequest): OrderCreateResponse {
        return orderService.order(request)
    }
}