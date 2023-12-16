package me.snowlight.springkotlindelivery.repository.cartItem

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.repository.cart.Cart
import me.snowlight.springkotlindelivery.repository.menu.Menu
import me.snowlight.springkotlindelivery.repository.store.Store
import java.time.LocalDateTime

@Entity
@Table(name = "cart_items")
class CartItem(
    cartId: Long,
    menuId: Long,
    storeId: Long,
    quantity: Int,
    isDeleted: Boolean,
    createdAt: LocalDateTime,
    createdBy: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    val id: Long = 0L
    val cartId: Long = cartId
    val menuId: Long = menuId
    val storeId: Long = storeId
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cart_id")
//    val cart: Cart = cart
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id")
//    val menu: Menu = menu
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_id")
//    val store: Store = store
    val quantity: Int = quantity
    val isDeleted: Boolean = isDeleted
    val createdAt: LocalDateTime = createdAt
    var updatedAt: LocalDateTime? = null
    val createdBy: String = createdBy
    var updatedBy: String? = null
}