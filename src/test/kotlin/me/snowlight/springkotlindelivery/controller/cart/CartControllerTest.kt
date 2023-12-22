package me.snowlight.springkotlindelivery.controller.cart

import com.fasterxml.jackson.databind.ObjectMapper
import me.snowlight.springkotlindelivery.controller.cart.dto.CartQueryResponse
import me.snowlight.springkotlindelivery.domain.catalog.menu.MenuStatus
import me.snowlight.springkotlindelivery.domain.customer.CustomerRole
import me.snowlight.springkotlindelivery.domain.customer.CustomerStatus
import me.snowlight.springkotlindelivery.domain.store.StoreStatus
import me.snowlight.springkotlindelivery.repository.cart.Cart
import me.snowlight.springkotlindelivery.repository.cart.CartRepository
import me.snowlight.springkotlindelivery.repository.cartItem.CartItem
import me.snowlight.springkotlindelivery.repository.cartItem.CartItemRepository
import me.snowlight.springkotlindelivery.repository.customer.Customer
import me.snowlight.springkotlindelivery.repository.customer.CustomerRepository
import me.snowlight.springkotlindelivery.repository.menu.Menu
import me.snowlight.springkotlindelivery.repository.menu.MenuRepository
import me.snowlight.springkotlindelivery.repository.store.Store
import me.snowlight.springkotlindelivery.repository.store.StoreRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var storeRepository: StoreRepository

    @Autowired
    private lateinit var menuRepository: MenuRepository

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var cartRepository: CartRepository

    @Autowired
    private lateinit var cartItemRepository: CartItemRepository

    private lateinit var mockMvc: MockMvc
    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilters<DefaultMockMvcBuilder?>(CharacterEncodingFilter("UTF-8", true))
            .build()
    }

    @AfterEach
    fun tearDown() {}

    @DisplayName("장바구니 메뉴 조회 테스트")
    @Test
    fun list() {
        val storeId = 1L
        val menuId = 1L
        val customerId = 1L
        val cartId = 1L
        val cartItemId = 1L

        val storeSaved = storeRepository.save(makeNewStore(storeId))
        val menu = menuRepository.save(makeNewMenu(menuId, storeSaved))
        val customer = customerRepository.save(makeNewCustomer(customerId))
        cartRepository.save(makeNewCart(cartId, customerId))
        cartItemRepository.save(makeNewCartItem(cartItemId, cartId, storeId, menuId))

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/apis/carts/items")
            .contentType(MediaType.APPLICATION_JSON)
            .param("customerId", customerId.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val cartQueryResponse = objectMapper.readValue(
            result.response.contentAsString,
            CartQueryResponse::class.java
        )
        assertThat(cartQueryResponse.customerId).isEqualTo(customerId)
        assertThat(cartQueryResponse.cartItems.size).isGreaterThanOrEqualTo(1)
        cartQueryResponse.cartItems.forEach {
            assertThat(it.menuId).isEqualTo(menu.menuId)
            assertThat(it.menuName).isEqualTo(menu.menuName)
            assertThat(it.menuImageUrl).isEqualTo(menu.menuMainImageUrl)
        }
    }

    private fun makeNewCart(cartId: Long, customerId: Long) = Cart(
        cartId = cartId,
        customerId = customerId
    )

    private fun makeNewCartItem(
        cartItemId: Long,
        cartId: Long,
        storeId: Long,
        menuId: Long
    ) = CartItem(
        cartItemId = cartItemId,
        cartId = cartId,
        storeId = storeId,
        menuId = menuId,
        quantity = 1000
    )

    private fun makeNewCustomer(customerId: Long) = Customer(
        customerId = customerId,
        name = "없음",
        email = "test@gmail.com",
        password = "password",
        phone = "010-222-5555",
        address = "no 주소",
        customerStatus = CustomerStatus.ACTIVE,
        customerRole = CustomerRole.CUSTOMER,
    )

    private fun makeNewMenu(
        menuId: Long,
        storeSaved: Store
    ) = Menu(
        menuId = menuId,
        menuName = "라면",
        storeId = storeSaved.storeId,
        price = BigDecimal(10_000),
        menuStatus = MenuStatus.READY,
        description = "sdfsdf",
        menuMainImageUrl = "/menu/img/1",
    )

    private fun makeNewStore(storeId: Long): Store {
        return Store(
            storeId,
            "test@naver.com",
            "111222",
            "test",
            "010-111-2222",
            "경기도 인천시",
            "1234",
            "수이 계정",
            "수이 은행",
            "ORDER",
            "descriop",
            StoreStatus.INIT,
            BigDecimal(10_000),
            "1100",
            5,
            BigDecimal(15_000),
        )
    }
}