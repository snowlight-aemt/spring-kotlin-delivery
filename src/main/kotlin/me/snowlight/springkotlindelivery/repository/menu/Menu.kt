package me.snowlight.springkotlindelivery.repository.menu

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.domain.catalog.menu.MenuStatus
import me.snowlight.springkotlindelivery.repository.BaseEntity
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "menus", catalog = "food_delivery")
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", nullable = false)
    val menuId: Long = -1,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(name = "menu_name", nullable = false)
    val menuName: String,

    @Column(name = "menu_main_image", nullable = false)
    val menuMainImageUrl: String,

    @Column(name = "price", nullable = false)
    val price: BigDecimal,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    val menuStatus: MenuStatus = MenuStatus.READY,

    @Column(name = "description", nullable = false)
    val description: String,
): BaseEntity()