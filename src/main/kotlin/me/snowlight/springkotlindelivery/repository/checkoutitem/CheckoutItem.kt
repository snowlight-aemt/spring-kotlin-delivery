package me.snowlight.springkotlindelivery.repository.checkoutitem

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.repository.BaseEntity
import java.math.BigDecimal

@Entity
@Table(name = "checkout_items")
class CheckoutItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_item_id", nullable = false)
    val checkoutItemId: Long = 0L,
    @Column(name = "checkout_id", nullable = false)
    val checkoutId: Long,
    @Column(name = "menu_id", nullable = false)
    val menuId: Long,
    @Column(name = "menu_price", nullable = false)
    val menuPrice: BigDecimal,
    @Column(name = "menu_quantity", nullable = false)
    val menuQuantity: Int,
): BaseEntity()
