package me.snowlight.springkotlindelivery.controller.display.mdp

import com.fasterxml.jackson.databind.ObjectMapper
import me.snowlight.springkotlindelivery.controller.display.mdp.dto.MenuDetailResponse
import me.snowlight.springkotlindelivery.domain.catalog.menu.MenuStatus
import me.snowlight.springkotlindelivery.domain.store.StoreStatus
import me.snowlight.springkotlindelivery.repository.menu.Menu
import me.snowlight.springkotlindelivery.repository.store.Store
import me.snowlight.springkotlindelivery.repository.menu.MenuRepository
import me.snowlight.springkotlindelivery.repository.store.StoreRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.OffsetDateTime

@SpringBootTest
@AutoConfigureMockMvc
class MenuDetailPageControllerTest {
    @Autowired
    private lateinit var mockMock: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var storeRepository: StoreRepository

    @Autowired
    private lateinit var menuRepository: MenuRepository

    @BeforeEach
    fun setup() {}

    @AfterEach
    fun tearDown() {}

    @DisplayName("메뉴 상세 페이지")
    @Test
    fun detail() {
        val storeId = 1L
        val menuId = 1L

        val storeSaved = storeRepository.save(makeNewStore(storeId))
        val menu = menuRepository.save(makeNewMenu(menuId, storeSaved))

        val result = mockMock.perform(get("/display/menu/$menuId"))
            .andExpect(status().isOk)
            .andReturn()


        val menuDetailResponse = objectMapper.readValue(
            result.response.contentAsString,
            MenuDetailResponse::class.java
        )

        Assertions.assertThat(menuDetailResponse).isNotNull
        Assertions.assertThat(menuDetailResponse.menuId).isEqualTo(menu.menuId)
    }

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