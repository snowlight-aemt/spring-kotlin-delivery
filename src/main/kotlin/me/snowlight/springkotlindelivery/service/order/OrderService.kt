package me.snowlight.springkotlindelivery.service.order

import me.snowlight.springkotlindelivery.controller.order.dto.OrderCreateRequest
import me.snowlight.springkotlindelivery.controller.order.dto.OrderCreateResponse
import me.snowlight.springkotlindelivery.domain.order.OrderStatus
import me.snowlight.springkotlindelivery.exception.NotFoundCheckoutException
import me.snowlight.springkotlindelivery.exception.NotFoundStoreException
import me.snowlight.springkotlindelivery.repository.checkout.Checkout
import me.snowlight.springkotlindelivery.repository.checkout.CheckoutRepository
import me.snowlight.springkotlindelivery.repository.checkoutitem.CheckoutItem
import me.snowlight.springkotlindelivery.repository.checkoutitem.CheckoutItemRepository
import me.snowlight.springkotlindelivery.repository.order.Order
import me.snowlight.springkotlindelivery.repository.order.OrderRepository
import me.snowlight.springkotlindelivery.repository.orderItem.OrderItem
import me.snowlight.springkotlindelivery.repository.orderItem.OrderItemRepository
import me.snowlight.springkotlindelivery.repository.store.Store
import me.snowlight.springkotlindelivery.repository.store.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val checkoutRepository: CheckoutRepository,
    private val checkoutItemRepository: CheckoutItemRepository,
    private val storeRepository: StoreRepository,
) {
    fun order(request: OrderCreateRequest): OrderCreateResponse {
        val checkout = checkoutRepository.findByIdOrNull(request.checkoutId) ?: throw NotFoundCheckoutException()
        val checkoutItems = checkoutItemRepository.findAllByCheckoutId(checkoutId = checkout.checkoutId)
        val store = storeRepository.findByIdOrNull(checkout.storeId) ?: throw NotFoundStoreException()

        val orderAmount = checkoutItems.sumOf { it.menuPrice.multiply(it.menuQuantity.toBigDecimal()) }

        // TODO 할인 가격 조회 기능 추가
        val discountAmount = BigDecimal(0);
        val uuid = UUID.randomUUID().toString()

        val order = createOrder(uuid, checkout, orderAmount, discountAmount, store)
        val orderSaved = orderRepository.save(order)

        checkoutItems.forEach {
            val orderItem = createOrderItem(it, orderSaved.orderId)
            orderItemRepository.save(orderItem)
        }

        // TODO OrderItem 추가햐
        return OrderCreateResponse(
            orderId = orderSaved.orderId,
            orderUUID = orderSaved.orderUUID,
            orderShortenId = orderSaved.orderShortenId,
            checkoutId = orderSaved.checkoutId,
            orderAmount = orderSaved.orderAmount,
            discountAmount = orderSaved.discountAmount,
            deliveryFee = orderSaved.deliveryFee,
            totalAmount = orderSaved.totalAmount,
            orderStatus = orderSaved.orderStatus,
            storeId = orderSaved.storeId,
            customerId = orderSaved.customerId,
        )
    }

    private fun createOrder(
        uuid: String,
        checkout: Checkout,
        orderAmount: BigDecimal,
        discountAmount: BigDecimal,
        store: Store,
    ): Order {
        val order = Order(
            orderUUID = uuid,
            orderShortenId = uuid.substring(0, 8),
            checkoutId = checkout.checkoutId,
            orderAmount = orderAmount,
            discountAmount = discountAmount,
            deliveryFee = store.deliveryFee,
            totalAmount = orderAmount.minus(discountAmount),
            orderStatus = OrderStatus.READY,
            customerId = checkout.storeId,
            storeId = checkout.storeId,
        )

        order.isDeleted = false
        order.createdBy = "test"
        order.updatedBy = "test"
        order.createdAt = OffsetDateTime.now()
        order.updatedAt = OffsetDateTime.now()
        return order
    }

    private fun createOrderItem(
        checkoutItem: CheckoutItem,
        orderId: Long
    ): OrderItem {
        val orderItem = OrderItem(
            orderId = orderId,
            menuId = checkoutItem.menuId,
            menuPrice = checkoutItem.menuPrice,
            menuQuantity = checkoutItem.menuQuantity,
        )

        orderItem.isDeleted = false
        orderItem.createdBy = "test"
        orderItem.updatedBy = "test"
        orderItem.createdAt = OffsetDateTime.now()
        orderItem.updatedAt = OffsetDateTime.now()
        return orderItem
    }
}
