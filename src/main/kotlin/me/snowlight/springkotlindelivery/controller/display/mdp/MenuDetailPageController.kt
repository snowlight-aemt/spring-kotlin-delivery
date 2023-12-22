package me.snowlight.springkotlindelivery.controller.display.mdp

import io.swagger.v3.oas.annotations.tags.Tag
import me.snowlight.springkotlindelivery.controller.display.mdp.dto.MenuDetailResponse
import me.snowlight.springkotlindelivery.exception.MenuNotIncludedInStore
import me.snowlight.springkotlindelivery.service.MenuService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "MenuDetailPageController", description = "메뉴 상세 페이")
@RestController
class MenuDetailPageController(
    private val menuService: MenuService,
) {
    @GetMapping("/apis/display/menu/{menuId}")
    fun detail(
        @PathVariable menuId: Long,
        @RequestParam storeId: Long,
    ): ResponseEntity<MenuDetailResponse> {
        val menu = menuService.getMenuById(menuId);

        if (menu.storeId != storeId)
            throw MenuNotIncludedInStore();

        return ok(MenuDetailResponse(
            menuId = menu.menuId,
            menuName = menu.menuName,
            storeId = menu.storeId,
            price = menu.price,
            menuStatus = menu.menuStatus,
            description = menu.description,
            menuImageUrl = menu.menuMainImageUrl,
        ))
    }
}