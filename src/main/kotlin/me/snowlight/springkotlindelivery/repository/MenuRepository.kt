package me.snowlight.springkotlindelivery.repository

import me.snowlight.springkotlindelivery.domain.menu.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
    fun findByStoreId(storeId: Long): List<Menu>
}
