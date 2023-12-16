package me.snowlight.springkotlindelivery.repository.customer

import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long> {
}