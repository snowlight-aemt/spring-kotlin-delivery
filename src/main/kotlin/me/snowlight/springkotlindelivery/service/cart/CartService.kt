package me.snowlight.springkotlindelivery.service.cart

import me.snowlight.springkotlindelivery.exception.NotFoundCustomerException
import me.snowlight.springkotlindelivery.repository.cart.Cart
import me.snowlight.springkotlindelivery.repository.cart.CartRepository
import me.snowlight.springkotlindelivery.repository.cartItem.CartItem
import me.snowlight.springkotlindelivery.repository.cartItem.CartItemRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
) {
    @Value("\${server.role-name}")
    private lateinit var serverRoleName: String

    fun getCartByCustomerId(customerId: Long): Cart {
        return cartRepository.findByCustomerIdAndIsDeleted(customerId, false)?: throw NotFoundCustomerException()
    }

    fun upset(addCartItem: AddCartItem): Unit {
        val cartSaved = cartRepository.findByCustomerIdAndIsDeleted(addCartItem.customerId, false) ?: newCart(addCartItem.customerId)

        val cartItem = CartItem(
            cartId = cartSaved.cartId,
            menuId = addCartItem.menuId,
            quantity = addCartItem.quantity,
            storeId = addCartItem.storeId,
        )
        cartItem.createdBy = serverRoleName
        cartItem.updatedBy = serverRoleName

        cartItemRepository.save(cartItem)
    }

    fun newCart(customerId: Long): Cart {
        val cart = Cart(customerId = customerId)
        cart.createdBy = serverRoleName
        cart.updatedBy = serverRoleName

        return cartRepository.save(cart)
    }
}
