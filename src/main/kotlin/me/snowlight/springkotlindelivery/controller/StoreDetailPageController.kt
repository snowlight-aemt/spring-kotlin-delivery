package me.snowlight.springkotlindelivery.controller

import io.swagger.v3.oas.annotations.tags.Tag
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
    @GetMapping("/display/stores/{storeId}")
    fun store(@PathVariable storeId: Long) : ResponseEntity<StoreDetailResponse> {
        val store = storeService.getStore(storeId)
        val menus = menuService.getMenuByStoreId(store.id)

        return ok(StoreDetailResponse(storeId = store.id,
            storeName = store.name,
            phone = store.phone,
            address = store.address,
            storeMainImageUrl = store.storeMainImageUrl,
            description = store.description,
            menus = menus.map { MenuDTO(it) }
        ))
    }
}