package me.snowlight.springkotlindelivery.service

import me.snowlight.springkotlindelivery.repository.store.Store
import me.snowlight.springkotlindelivery.exception.NotFoundStoreException
import me.snowlight.springkotlindelivery.repository.store.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StoreService(
    private val storeRepository: StoreRepository,
) {
    fun getStore(storeId: Long): Store {
        return storeRepository.findByIdOrNull(storeId)?: throw NotFoundStoreException();
    }
}