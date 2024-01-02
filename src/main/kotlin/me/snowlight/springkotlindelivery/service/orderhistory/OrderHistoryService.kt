package me.snowlight.springkotlindelivery.service.orderhistory

import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryRequest
import me.snowlight.springkotlindelivery.exception.NotFoundOrderException
import me.snowlight.springkotlindelivery.repository.order.OrderRepository
import me.snowlight.springkotlindelivery.repository.orderItem.OrderItemRepository
import me.snowlight.springkotlindelivery.service.orderhistory.dto.OrderHistory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional(readOnly = true)
class OrderHistoryService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
) {
    fun list(request: OrderHistoryRequest): List<OrderHistory> {
        val orderStoreList = orderRepository.findAllByCustomerId(request.customerId, request.orderStatus)

        return orderStoreList.map { it ->
            val orderItemMenu = orderItemRepository.findAllByOrderId(it.orderId)
            val menuNames = orderItemMenu.map { it.menuName }
            val menuUrl = orderItemMenu.first().menuMainImageUrl

            OrderHistory(
                orderId = it.orderId,
                storeId = it.storeId,
                storeName = it.storeName,
                menuCount = orderStoreList.size,
                menuNames = menuNames,
                menuRepresentativeImageUrl = menuUrl,
                totalOrderAmount = it.orderTotalAmount,
                orderStatus = it.orderStatus,
            )
        }
    }

    fun detail(orderId: Long): OrderHistory {
        val order = orderRepository.findByOrderId(orderId) ?: throw NotFoundOrderException()
        val orderItemMenu = orderItemRepository.findAllByOrderId(orderId)

        val menuMainImageUrl = orderItemMenu.first().menuMainImageUrl
        val menuNames = orderItemMenu.map { it.menuName }

        return OrderHistory(
            orderId = order.orderId,
            storeId = order.storeId,
            storeName = order.storeName,
            menuCount = menuNames.size,
            menuNames = menuNames,
            menuRepresentativeImageUrl = menuMainImageUrl,
            totalOrderAmount = order.orderTotalAmount,
            orderStatus = order.orderStatus,
        )
    }
}