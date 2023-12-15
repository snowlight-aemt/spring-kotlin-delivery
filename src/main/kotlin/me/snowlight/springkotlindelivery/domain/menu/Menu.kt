package me.snowlight.springkotlindelivery.domain.menu

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.domain.store.Store
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All

@Entity
@Table(name = "menus")
class Menu (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    var id: Long,
    var menuName: String,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "storeId")
    var store: Store,
    var price: BigDecimal,
    var status: String,
    var isDeleted: Boolean,
    var description: String,
    var menuImageUrl: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime?,
    var createdBy: String,
    var updatedBy: String?,
)
