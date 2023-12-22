package me.snowlight.springkotlindelivery.repository.cartItem

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.repository.BaseEntity

@Entity
@Table(name = "cart_items")
class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    val cartItemId: Long = -1,

    @Column(name = "cart_id")
    val cartId: Long,

    @Column(name = "store_id")
    val storeId: Long,

    @Column(name = "menu_id")
    val menuId: Long,

    @Column(name = "quantity")
    var quantity: Int,

): BaseEntity()