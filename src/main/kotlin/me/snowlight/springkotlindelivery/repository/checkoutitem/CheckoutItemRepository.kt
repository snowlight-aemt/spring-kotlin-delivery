package me.snowlight.springkotlindelivery.repository.checkoutitem

import org.springframework.data.jpa.repository.JpaRepository

interface CheckoutItemRepository: JpaRepository<CheckoutItem, Long> {

}
