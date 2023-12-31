package me.snowlight.springkotlindelivery.repository.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.domain.order.OrderStatus
import me.snowlight.springkotlindelivery.repository.BaseEntity
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    val orderId: Long = 0,

    @Column(name = "order_uuid", nullable = false)
    val orderUUID: String,

    @Column(name = "order_shorten_id", nullable = false)
    val orderShortenId: String,

    @Column(name = "checkout_id", nullable = false)
    val checkoutId: Long,

    @Column(name = "order_amount", nullable = false)
    val orderAmount: BigDecimal,

    @Column(name = "discount_amount", nullable = false)
    val discountAmount: BigDecimal,

    @Column(name = "delivery_fee", nullable = false)
    val deliveryFee: BigDecimal,

    @Column(name = "total_order_amount", nullable = false)
    val totalAmount: BigDecimal,

    @Column(name = "order_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    val orderStatus: OrderStatus = OrderStatus.READY,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(name = "customer_id", nullable = false)
    val customerId: Long,
): BaseEntity()