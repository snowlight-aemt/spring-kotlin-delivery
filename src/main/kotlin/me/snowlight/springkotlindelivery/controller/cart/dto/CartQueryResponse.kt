package me.snowlight.springkotlindelivery.controller.cart.dto

import me.snowlight.springkotlindelivery.domain.cart.CartMenu
import java.math.BigDecimal

data class CartQueryResponse (
    val customerId: Long,
    val cartItems: List<CartMenuDTO>
)

data class CartMenuDTO (
    val cartItemId: Long,
    val menuId: Long,
    val menuName: String,
    val menuImageUrl: String,
    val quantity: Int,
    val totalPrice: BigDecimal,
) {
    companion object {
        fun from(cartItem: CartMenu): CartMenuDTO {
            return CartMenuDTO(
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