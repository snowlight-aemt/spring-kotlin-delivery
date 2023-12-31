package me.snowlight.springkotlindelivery.repository.checkout

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CheckoutRepository: JpaRepository<Checkout, Long> {
    fun findAllByCheckoutIdIsNotAndCustomerIdIs(checkoutId: Long, customerId: Long): List<Checkout>

    @Query("""
        select c
          from Checkout c
         where c.customerId = :customerId
           and c.isDeleted = false
    """)
    fun findByCustomerId(customerId: Long): Checkout?
}
