package me.snowlight.springkotlindelivery.repository.order

import me.snowlight.springkotlindelivery.domain.order.OrderStatus
import me.snowlight.springkotlindelivery.domain.order.OrderStore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository: JpaRepository<Order, Long> {
    @Query("""
        select new me.snowlight.springkotlindelivery.domain.order.OrderStore(
            o.orderId, o.orderStatus, o.totalAmount, o.discountAmount, s.storeId, s.storeName
        )
          from Order o
          join Store s
            on o.storeId = s.storeId
         where o.customerId = :customerId
           and o.orderStatus = :orderStatus
           and o.isDeleted = false
    """)
    fun findAllByCustomerId(customerId: Long, orderStatus: OrderStatus): List<OrderStore>

    @Query("""
        select new me.snowlight.springkotlindelivery.domain.order.OrderStore(
            o.orderId, o.orderStatus, o.totalAmount, o.discountAmount, s.storeId, s.storeName
        )
          from Order o
          join Store s
            on o.storeId = s.storeId
         where o.orderId = :orderId
           and o.isDeleted = false
    """)
    abstract fun findByOrderId(orderId: Long): OrderStore?
}