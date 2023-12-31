package me.snowlight.springkotlindelivery.repository.checkoutitem

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CheckoutItemRepository: JpaRepository<CheckoutItem, Long> {
    fun findByCheckoutId(checkoutId: Long): List<CheckoutItem>
    @Query("""
        select ci
          from CheckoutItem ci
         where ci.checkoutId = :checkoutId
           and ci.isDeleted = false
    """)
    fun findAllByCheckoutId(checkoutId: Long): List<CheckoutItem>

}
