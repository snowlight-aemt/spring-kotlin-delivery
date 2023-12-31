package me.snowlight.springkotlindelivery

import com.fasterxml.jackson.databind.ObjectMapper
import me.snowlight.springkotlindelivery.controller.display.sdp.StoreDetailPageController
import me.snowlight.springkotlindelivery.controller.display.sdp.dto.StoreDetailResponse
import me.snowlight.springkotlindelivery.domain.store.StoreStatus
import me.snowlight.springkotlindelivery.repository.store.Store
import me.snowlight.springkotlindelivery.repository.store.StoreRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class SpringKotlinDeliveryApplicationTests {
    @Autowired
    private lateinit var mockMock: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var storeDetailPageController: StoreDetailPageController

    @Autowired
    private lateinit var storeRepository: StoreRepository

    @BeforeEach
    fun setup() {}

    @AfterEach
    fun tearDown() {}

    @DisplayName("상품 상세 정보와 메뉴를 조회할 수 있습니다.")
    @Test
    fun contextLoads() {
        val storeId = 1L
        storeRepository.save(makeNewStore(storeId))

        val result = mockMock.perform(get("/display/stores/${storeId}"))
            .andExpect(status().isOk)
            .andReturn()

        val restoreDetailResponse = objectMapper.readValue(
                result.response.contentAsString,
        StoreDetailResponse::class.java
        )

        Assertions.assertThat(restoreDetailResponse).isNotNull
        Assertions.assertThat(restoreDetailResponse.storeId).isEqualTo(storeId)
        Assertions.assertThat(restoreDetailResponse.menus).isNotNull
        Assertions.assertThat(restoreDetailResponse.menus.size).isGreaterThanOrEqualTo(0)
    }

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
            "DESCRIPT",
            StoreStatus.READY,
            BigDecimal(10_000),
            "1100",
            5,
            BigDecimal(15_000),
        )
    }

}
