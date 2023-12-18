package me.snowlight.springkotlindelivery.repository.checkoutdiscount

import org.springframework.data.jpa.repository.JpaRepository

interface CheckoutDiscountItemRepository: JpaRepository<CheckoutDiscountItem, Long> {

}
