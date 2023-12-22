package me.snowlight.springkotlindelivery.controller.display.sdp

import io.swagger.v3.oas.annotations.tags.Tag
import me.snowlight.springkotlindelivery.controller.category.menu.dto.MenuDTO
import me.snowlight.springkotlindelivery.controller.display.sdp.dto.StoreDetailResponse
import me.snowlight.springkotlindelivery.service.MenuService
import me.snowlight.springkotlindelivery.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "StoreDetailPageCotnroller")
@RestController
class StoreDetailPageController(
    val storeService: StoreService,
    val menuService: MenuService,
) {
    @GetMapping("/apis/display/stores/{storeId}")
    fun store(@PathVariable storeId: Long) : ResponseEntity<StoreDetailResponse> {
        val store = storeService.getStore(storeId)
        val menus = menuService.getMenuByStoreId(store.storeId)

        return ok(StoreDetailResponse(
            storeId = store.storeId,
            storeName = store.storeName,
            phone = store.storePhone,
            address = store.address,
            storeMainImageUrl = store.storeMainImageUrl,
            description = store.description,
            deliveryFee = store.deliveryFee,
            deliveryTime = store.deliveryTime,
            reviewGrade = store.reviewGrade,
            minimumOrderPrice = store.minimumOrderPrice,
            menus = menus.map { MenuDTO(it) }
        ))
    }
}