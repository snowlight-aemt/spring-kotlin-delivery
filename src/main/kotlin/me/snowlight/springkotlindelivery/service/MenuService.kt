package me.snowlight.springkotlindelivery.service

import me.snowlight.springkotlindelivery.domain.menu.Menu
import me.snowlight.springkotlindelivery.repository.MenuRepository
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
}