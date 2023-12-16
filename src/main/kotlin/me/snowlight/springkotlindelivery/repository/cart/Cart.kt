package me.snowlight.springkotlindelivery.repository.cart

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import me.snowlight.springkotlindelivery.repository.customer.Customer
import java.time.LocalDateTime

@Entity
@Table(name = "carts")
class Cart(
    customer: Customer,
    isDeleted: Boolean,
    createdAt: LocalDateTime,
    createdBy: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    val id: Long = 0L
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id")
    val customer: Customer = customer
    val isDeleted: Boolean = isDeleted
    val createdAt: LocalDateTime = createdAt
    var updatedAt: LocalDateTime? = null
    val createdBy: String = createdBy
    var updatedBy: String? = null
}
