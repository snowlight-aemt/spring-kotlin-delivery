package me.snowlight.springkotlindelivery.controller.cart

import io.swagger.v3.oas.annotations.tags.Tag
import me.snowlight.springkotlindelivery.controller.cart.dto.CartItemDto
import me.snowlight.springkotlindelivery.controller.cart.dto.CartQueryRequest
import me.snowlight.springkotlindelivery.controller.cart.dto.CartResponse
import me.snowlight.springkotlindelivery.service.cart.CartService
import me.snowlight.springkotlindelivery.service.cartItem.CartItemService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CartController")
@RestController
class CartController(
    private val cartService: CartService,
    private val cartItemService: CartItemService,
) {
    @GetMapping("/apis/carts/items")
    fun list(@ModelAttribute request: CartQueryRequest): ResponseEntity<CartResponse>
    {
        // TODO 고객 존재 검증
        // TODO 장바구니 조회 서비스 호출
        val cart = cartService.getCartByCustomerId(request.customerId)
        val items = cartItemService.getItemsByCartId(cart.id)
        val itemsDto = items.map { CartItemDto.from(it) }
        return ok(CartResponse(
            responseCode = 200,
            responseMessage = "장바구니",
            data = itemsDto,
        ))

//        return ok(CartResponse(
//            responseCode = 200,
//            responseMessage = "장바구니",
//            data = listOf(
//                CartDto(
//                    cartItemId = 1L,
//                    menuId = 1L,
//                    menuName = "라면",
//                    menuImageUrl = "/img/cart/01",
//                    quantity = 10,
//                    totalPrice = BigDecimal(100_000),
//                )
//            ),
//        ))
    }
}