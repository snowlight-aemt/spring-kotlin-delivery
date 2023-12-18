package me.snowlight.springkotlindelivery.repository.checkout

import org.springframework.data.jpa.repository.JpaRepository

interface CheckoutRepository: JpaRepository<Checkout, Long> {
    fun findAllByCheckoutIdIsNotAndCustomerIdIs(checkoutId: Long, customerId: Long): List<Checkout>
}
