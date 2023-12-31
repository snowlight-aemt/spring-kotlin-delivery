package me.snowlight.springkotlindelivery.repository.cartItem

import me.snowlight.springkotlindelivery.domain.cart.CartMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CartItemRepository: JpaRepository<CartItem, Long> {
    @Query("""
        select new me.snowlight.springkotlindelivery.domain.cart.CartMenu(
            c.cartId,
            c.cartItemId,
            c.menuId,
            m.menuName,
            m.menuMainImageUrl,
            m.price,
            c.quantity)
        from CartItem c
        left join Menu m
          on c.menuId = m.menuId
        where c.cartId = :cartId
          and c.isDeleted = false
          and m.isDeleted = false
    """)
    fun findByCartId(cartId: Long): List<CartMenu>
}