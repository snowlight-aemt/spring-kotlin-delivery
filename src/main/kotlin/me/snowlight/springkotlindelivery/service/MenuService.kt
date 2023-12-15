package me.snowlight.springkotlindelivery.service

import me.snowlight.springkotlindelivery.repository.menu.Menu
import me.snowlight.springkotlindelivery.exception.NotFoundMenuException
import me.snowlight.springkotlindelivery.repository.menu.MenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MenuService(
    private val menuRepository: MenuRepository,
) {
    fun getMenuByStoreId(storeId: Long): List<Menu> {
        return menuRepository.findByStoreId(storeId)
    }

    fun getMenuById(menuId: Long): Menu {
        return menuRepository.findByIdOrNull(menuId)?: throw NotFoundMenuException()
    }
}