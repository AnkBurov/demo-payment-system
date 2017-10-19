package io.demo.paymentsystem.service.payment

import io.demo.paymentsystem.api.PaymentCreationRequest
import io.demo.paymentsystem.api.document.PaymentDto
import io.demo.paymentsystem.api.document.Payment

/**
 * Service for payments
 */
interface PaymentService {

    /**
     * Create payment from one account to another
     *
     * @param creationRequest request
     * @return created payment dto
     */
    fun createPayment(creationRequest: PaymentCreationRequest): PaymentDto

    /**
     * Approve all payments with status CREATED
     *
     * Kinda mocking bank systems
     * @return handled payments
     */
    fun approveCreatedPayments(): List<Payment>
}