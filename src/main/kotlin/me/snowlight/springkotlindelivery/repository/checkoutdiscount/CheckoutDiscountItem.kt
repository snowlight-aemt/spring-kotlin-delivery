package me.snowlight.springkotlindelivery.repository.checkoutdiscount

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.repository.BaseEntity

@Entity
@Table(name = "checkout_discount_items")
class CheckoutDiscountItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_discount_item", nullable = false)
    val checkoutDiscountItem: Long = 0L,
    @Column(name = "checkout_id", nullable = false)
    val checkoutId: Long,
    @Column(name = "discount_id", nullable = false)
    val discountId: Long,
): BaseEntity()
