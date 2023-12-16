package me.snowlight.springkotlindelivery.service.cartItem

import me.snowlight.springkotlindelivery.domain.cart.CartMenu
import me.snowlight.springkotlindelivery.repository.cartItem.CartItemRepository
import org.springframework.stereotype.Service

@Service
class CartItemService(
    private val cartItemRepository: CartItemRepository,
) {
    fun getItemsByCartId(cartId: Long): List<CartMenu> {
        return cartItemRepository.findByCartId(cartId)
    }
}
