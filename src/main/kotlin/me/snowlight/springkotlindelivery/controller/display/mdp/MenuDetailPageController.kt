package me.snowlight.springkotlindelivery.controller.display.mdp

import io.swagger.v3.oas.annotations.tags.Tag
import me.snowlight.springkotlindelivery.controller.display.mdp.dto.MenuDetailResponse
import me.snowlight.springkotlindelivery.service.MenuService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "MenuDetailPageController", description = "메뉴 상세 페이")
@RestController
class MenuDetailPageController(
    private val menuService: MenuService,
) {
    @GetMapping("/display/menu/{menuId}")
    fun detail(@PathVariable menuId: Long): ResponseEntity<MenuDetailResponse> {
        val menu = menuService.getMenuById(menuId);
        return ok(MenuDetailResponse(
            menuId = menu.id,
            menuName = menu.menuName,
            storeId = menu.store.id,
            price = menu.price,
            menuStatus = menu.status,
            description = menu.description,
            menuImageUrl = menu.menuImageUrl,
        ))
    }
}