package me.snowlight.springkotlindelivery.controller.customer

import io.swagger.v3.oas.annotations.tags.Tag
import me.snowlight.springkotlindelivery.controller.customer.dto.CustomerResponse
import me.snowlight.springkotlindelivery.service.customer.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CustomerController", description = "고객 정보 컨트롤")
@RestController
class CustomerController(
    private val customerService: CustomerService,
) {
    @GetMapping("/apis/customer/{customerId}")
    fun detail(@PathVariable customerId: Long): ResponseEntity<CustomerResponse> {
        val customer = customerService.getById(customerId)
        return ResponseEntity.ok(CustomerResponse(
            customerId = customer.customerId,
            address = customer.address
        ))
    }
}
