package me.snowlight.springkotlindelivery.service.orderhistory

import me.snowlight.springkotlindelivery.controller.orderhistory.dto.OrderHistoryRequest
import me.snowlight.springkotlindelivery.domain.order.OrderStatus
import me.snowlight.springkotlindelivery.repository.checkout.CheckoutRepository
import me.snowlight.springkotlindelivery.repository.checkoutdiscount.CheckoutDiscountItemRepository
import me.snowlight.springkotlindelivery.repository.checkoutitem.CheckoutItemRepository
import me.snowlight.springkotlindelivery.repository.order.OrderRepository
import me.snowlight.springkotlindelivery.repository.orderItem.OrderItemRepository
import me.snowlight.springkotlindelivery.service.orderhistory.dto.OrderHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderHistoryService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val checkoutRepository: CheckoutRepository,
    private val checkoutItemResponse: CheckoutItemRepository,
    private val discountItemRepository: CheckoutDiscountItemRepository,
) {
    fun list(request: OrderHistoryRequest): List<OrderHistory> {
        val orderStoreList = orderRepository.findAllByOrderId(request.customerId, OrderStatus.READY)

        return orderStoreList.map { it ->
            val orderItemMenu = orderItemRepository.findByIdOrIsDeleted(it.orderId)
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
}