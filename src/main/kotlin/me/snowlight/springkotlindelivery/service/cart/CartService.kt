package me.snowlight.springkotlindelivery.service.cart

import me.snowlight.springkotlindelivery.exception.NotFoundCustomerException
import me.snowlight.springkotlindelivery.repository.cart.Cart
import me.snowlight.springkotlindelivery.repository.cart.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
) {
    fun getCartByCustomerId(customerId: Long): Cart {
        return cartRepository.findByCustomerId(customerId)?: throw NotFoundCustomerException()
    }
}
