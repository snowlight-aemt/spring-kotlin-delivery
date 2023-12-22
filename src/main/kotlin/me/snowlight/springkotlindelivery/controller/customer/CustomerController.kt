package me.snowlight.springkotlindelivery.controller.customer

import io.swagger.v3.oas.annotations.tags.Tag
import me.snowlight.springkotlindelivery.controller.customer.dto.CustomerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CustomerController", description = "고객 정보 컨트롤")
@RestController
class CustomerController {
    @GetMapping("/apis/customer/{customerId}")
    fun detail(@PathVariable customerId: Long): ResponseEntity<CustomerResponse> {
        return ResponseEntity.ok(CustomerResponse(
            customerId = 1L,
            address = "서울특별시 강남"
        ))
    }
}
