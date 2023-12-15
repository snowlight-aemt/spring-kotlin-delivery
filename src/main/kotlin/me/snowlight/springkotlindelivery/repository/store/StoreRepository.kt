package me.snowlight.springkotlindelivery.repository.store

import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long> {
}
