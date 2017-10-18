package io.demo.paymentsystem.controller

import io.demo.paymentsystem.api.PaymentCreationRequest
import io.demo.paymentsystem.api.document.PaymentDto
import io.demo.paymentsystem.service.payment.PaymentService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/payment")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping
    fun createPayment(@RequestBody @Valid creationRequest: PaymentCreationRequest): PaymentDto {
        return paymentService.createPayment(creationRequest)
    }
}