package me.snowlight.springkotlindelivery.controller.checkout

import io.swagger.v3.oas.annotations.Operation
import me.snowlight.springkotlindelivery.controller.checkout.dto.CheckoutRequest
import me.snowlight.springkotlindelivery.controller.checkout.dto.CheckoutResponse
import me.snowlight.springkotlindelivery.service.cart.CartService
import me.snowlight.springkotlindelivery.service.cartItem.CartItemService
import me.snowlight.springkotlindelivery.service.checkout.CheckoutService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController(
    private val checkoutService: CheckoutService,
    private val cartService: CartService,
    private val cartItemService: CartItemService,
) {
    @PostMapping("/apis/checkout")
    @Operation(
        summary = "체크아웃 API"
    )
    fun checkout(@RequestBody checkoutRequest: CheckoutRequest): ResponseEntity<CheckoutResponse> {
        val cart = cartService.getCartByCustomerId(checkoutRequest.customerId)
        val cartItems = cartItemService.getItemsByCartId(cart.cartId)
        val checkoutId = checkoutService.create(
            checkoutRequest.customerId,
            checkoutRequest.discountId,
            checkoutRequest.storeId,
            cartItems
        )

        return ok(CheckoutResponse(
            customerId = checkoutRequest.customerId,
            checkoutId = checkoutId,
        ))
    }
}