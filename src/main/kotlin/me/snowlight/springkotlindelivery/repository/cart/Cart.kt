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
import me.snowlight.springkotlindelivery.repository.BaseEntity
import me.snowlight.springkotlindelivery.repository.customer.Customer
import java.time.LocalDateTime

@Entity
@Table(name = "carts", catalog = "food_delivery")
class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    val cartId: Long = 0,

    @Column(name = "customer_id")
    val customerId: Long,

): BaseEntity()
