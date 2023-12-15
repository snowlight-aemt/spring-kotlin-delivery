package me.snowlight.springkotlindelivery.repository

import me.snowlight.springkotlindelivery.domain.store.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long> {
}
