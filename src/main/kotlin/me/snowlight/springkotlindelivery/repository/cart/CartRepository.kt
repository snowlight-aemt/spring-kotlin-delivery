package me.snowlight.springkotlindelivery.repository.cart

import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository: JpaRepository<Cart, Long> {
    fun findByCustomerIdAndIsDeleted(customerId: Long, isDelete: Boolean): Cart?
}