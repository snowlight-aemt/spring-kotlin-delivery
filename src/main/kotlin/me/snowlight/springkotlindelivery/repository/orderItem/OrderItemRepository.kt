package me.snowlight.springkotlindelivery.repository.orderItem

import me.snowlight.springkotlindelivery.repository.menuitem.OrderItemMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderItemRepository: JpaRepository<OrderItem, Long> {
    @Query("""
        select new me.snowlight.springkotlindelivery.repository.menuitem.OrderItemMenu(
            oi.orderId,
            oi.orderItemId,
            m.menuId,
            m.menuName,
            m.price,
            m.menuMainImageUrl
        )
          from OrderItem oi
          join Menu m
            on oi.menuId = m.menuId
         where oi.orderId = :orderId
           and oi.isDeleted = false
    """)
    fun findByIdOrIsDeleted(orderId: Long): List<OrderItemMenu>
}