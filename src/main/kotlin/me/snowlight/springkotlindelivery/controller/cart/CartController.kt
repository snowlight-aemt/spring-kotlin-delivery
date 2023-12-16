package me.snowlight.springkotlindelivery.controller.cart

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.persistence.Access
import jdk.jfr.ContentType
import me.snowlight.springkotlindelivery.controller.cart.dto.CartMenuDTO
import me.snowlight.springkotlindelivery.controller.cart.dto.CartQueryRequest
import me.snowlight.springkotlindelivery.controller.cart.dto.CartQueryResponse
import me.snowlight.springkotlindelivery.service.cart.CartService
import me.snowlight.springkotlindelivery.service.cartItem.CartItemService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CartController")
@RestController
class CartController(
    private val cartService: CartService,
    private val cartItemService: CartItemService,
) {
    @GetMapping(value = ["/apis/carts/items"])
    fun list(@ModelAttribute request: CartQueryRequest): ResponseEntity<CartQueryResponse>
    {
        // TODO feat 고객 존재 여부 검증
        val cart = cartService.getCartByCustomerId(request.customerId)
        val cartMenus = cartItemService.getItemsByCartId(cart.cartId)
        val cartMenusDTO = cartMenus.map { CartMenuDTO.from(it) }

        return ok(CartQueryResponse(
            customerId = request.customerId,
            menus = cartMenusDTO,
        ))
    }
}