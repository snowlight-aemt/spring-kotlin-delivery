package me.snowlight.springkotlindelivery.service.checkout

import me.snowlight.springkotlindelivery.domain.cart.CartMenu
import me.snowlight.springkotlindelivery.repository.checkout.Checkout
import me.snowlight.springkotlindelivery.repository.checkout.CheckoutRepository
import me.snowlight.springkotlindelivery.repository.checkoutdiscount.CheckoutDiscountItem
import me.snowlight.springkotlindelivery.repository.checkoutitem.CheckoutItemRepository
import me.snowlight.springkotlindelivery.repository.checkoutdiscount.CheckoutDiscountItemRepository
import me.snowlight.springkotlindelivery.repository.checkoutitem.CheckoutItem
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CheckoutService(
    private val checkoutRepository: CheckoutRepository,
    private val checkoutItemRepository: CheckoutItemRepository,
    private val checkoutDiscountItemRepository: CheckoutDiscountItemRepository,
) {
    @Value("\${server.role-name}")
    lateinit var roleName: String

    @Transactional
    fun create(customerId: Long, discountId: Long, storeId: Long, cartItems: List<CartMenu>): Long {
        val checkoutId = createCheckout(customerId, storeId)
        removeCheckoutExcept(customerId, checkoutId)

        createCheckoutItems(cartItems, checkoutId)
        createCheckoutDiscount(discountId, checkoutId)
        return checkoutId;
    }

    private fun createCheckout(
        customerId: Long,
        storeId: Long
    ): Long {
        val checkout = Checkout(
            customerId = customerId,
            storeId = storeId
        )
        checkout.createdBy = roleName
        checkout.updatedBy = roleName

        return checkoutRepository.save(checkout).checkoutId
    }

    /**
     * 이전에 고객이 체크아웃한 상품을 현재 체크아웃한 상품을 제외하고 삭제한다.
     */
    private fun removeCheckoutExcept(
        customerId: Long,
        checkoutId: Long
    ) {
        val checkouts = checkoutRepository.findAllByCheckoutIdIsNotAndCustomerIdIs(
            checkoutId = checkoutId,
            customerId = customerId,
        )

        checkouts.forEach { it.delete() }
        checkoutRepository.saveAll(checkouts)
    }

    private fun createCheckoutItems(
        cartItems: List<CartMenu>,
        checkoutId: Long
    ) {
        val checkoutItems = cartItems.map {
            val checkoutItem = CheckoutItem(
                checkoutId = checkoutId,
                menuId = it.menuId,
                menuPrice = it.price,
                menuQuantity = it.quantity,
            )
            checkoutItem.createdBy = roleName
            checkoutItem.updatedBy = roleName

            checkoutItem
        }

        checkoutItemRepository.saveAll(checkoutItems)
    }

    private fun createCheckoutDiscount(
        discountId: Long,
        checkoutId: Long
    ) {
        val checkoutDiscountItem = CheckoutDiscountItem(
            checkoutId = checkoutId,
            discountId = discountId,
        )
        checkoutDiscountItem.createdBy = roleName
        checkoutDiscountItem.updatedBy = roleName

        checkoutDiscountItemRepository.save(checkoutDiscountItem)
    }
}
