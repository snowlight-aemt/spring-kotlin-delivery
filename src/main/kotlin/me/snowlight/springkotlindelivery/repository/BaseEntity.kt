package me.snowlight.springkotlindelivery.repository

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.time.OffsetDateTime

@MappedSuperclass
abstract class BaseEntity {
    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now()

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()

    // TODO 생성자 이름을 초기화할 필요가 있다.
    @Column(name = "created_by", nullable = true)
    var createdBy: String? = null

    @Column(name = "updated_by", nullable = true)
    var updatedBy: String? = null
}