package me.snowlight.springkotlindelivery.repository.store

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "stores")
class Store (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    var id: Long = 0L,
    var email: String,
    var businessNumber: String,
    var name: String,
    var phone: String,
    var address: String,
    var password: String,
    var bankAccount: String,
    var bankName: String,
    var status: String,
    var isDeleted: Boolean,
    var storeMainImageUrl: String,
    var description: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime?,
    var createdBy: String,
    var updatedBy: String?
)