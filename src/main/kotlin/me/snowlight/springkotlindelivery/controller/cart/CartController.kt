package me.snowlight.springkotlindelivery.controller.cart

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import me.snowlight.springkotlindelivery.controller.cart.dto.CartMenuDTO
import me.snowlight.springkotlindelivery.controller.cart.dto.CartQueryRequest
import me.snowlight.springkotlindelivery.controller.cart.dto.CartQueryResponse
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
    @GetMapping(value = ["/apis/carts/items"])
    @Operation(
        summary = "장바구니 목록 요청",
        description = "현재 장바구니에 담긴 음식 메뉴 목록을 조회한다.",
        responses = [ApiResponse(
            responseCode = "200",
            description = "장바구니 요청에 대한 응답",
            useReturnTypeSchema = true,
        )]
    )
    fun list(
        @Parameter(
            schema = Schema(implementation = CartQueryRequest::class),
            name = "cartQueryRequest", description = "장바구니 조회 요청", required = true, `in` = ParameterIn.QUERY)
        @ModelAttribute request: CartQueryRequest,
    ): ResponseEntity<CartQueryResponse> {
        // TODO Pageable 이 필요.
        // TODO feat 고객 존재 여부 검증
        val cart = cartService.getCartByCustomerId(request.customerId)
        val cartMenus = cartItemService.getItemsByCartId(cart.cartId)
        val cartMenusDTO = cartMenus.map { CartMenuDTO.from(it) }

        return ok(CartQueryResponse(
            customerId = request.customerId,
            cartItems = cartMenusDTO,
        ))
    }
}