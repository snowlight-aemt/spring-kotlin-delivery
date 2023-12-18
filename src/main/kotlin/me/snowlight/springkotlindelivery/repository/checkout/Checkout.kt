package me.snowlight.springkotlindelivery.repository.checkout

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.repository.BaseEntity

@Entity
@Table(name = "checkouts")
class Checkout(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_id", nullable = false)
    val checkoutId : Long = 0L,
    @Column(name = "customer_id", nullable = false)
    val customerId : Long,
    @Column(name = "store_id", nullable = false)
    val storeId : Long,
): BaseEntity() {
    fun delete() {
        this.isDeleted = true
    }
}
