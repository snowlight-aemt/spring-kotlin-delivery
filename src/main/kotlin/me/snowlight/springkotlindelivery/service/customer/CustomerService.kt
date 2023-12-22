package me.snowlight.springkotlindelivery.service.customer

import me.snowlight.springkotlindelivery.exception.NotFoundCustomerException
import me.snowlight.springkotlindelivery.repository.customer.Customer
import me.snowlight.springkotlindelivery.repository.customer.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) {
    fun getById(customerId: Long): Customer {
        return customerRepository.findByIdOrNull(customerId)?: throw NotFoundCustomerException()
    }
}
