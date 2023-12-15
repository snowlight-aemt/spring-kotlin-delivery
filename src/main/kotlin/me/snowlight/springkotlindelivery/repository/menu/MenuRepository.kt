package me.snowlight.springkotlindelivery.repository.menu

import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
    fun findByStoreId(storeId: Long): List<Menu>
}
