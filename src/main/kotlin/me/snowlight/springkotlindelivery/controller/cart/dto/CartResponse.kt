package me.snowlight.springkotlindelivery.controller.cart.dto

import me.snowlight.springkotlindelivery.domain.cart.CartMenu
import me.snowlight.springkotlindelivery.repository.cartItem.CartItem
import java.math.BigDecimal

data class CartResponse (
    val responseCode: Int,
    val responseMessage: String,
    val data: List<CartItemDto>
)

data class CartItemDto (
    val cartItemId: Long,
    val menuId: Long,
    val menuName: String,
    val menuImageUrl: String,
    val quantity: Int,
    val totalPrice: BigDecimal,
) {
    companion object {
        fun from(cartItem: CartMenu): CartItemDto {
            return CartItemDto(
                cartItemId = cartItem.cartItemId,
                menuId = cartItem.menuId,
                menuName = cartItem.menuName,
                menuImageUrl = cartItem.menuImageUrl,
                quantity = cartItem.quantity,
                totalPrice = cartItem.price.multiply(BigDecimal(cartItem.quantity))
            )
        }
    }
}