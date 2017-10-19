package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.document.Payment
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult
import org.springframework.stereotype.Component

/**
 * Check that payer account and payee account are not the same account
 */
@Component
class NotTheSameAccountChecker : AbstractPaymentChecker() {

    override fun checkDocOperation(payment: Payment): CheckOperationResult {
        return if (payment.payer.accountNumber != payment.payee.accountNumber) {
            CheckOperationResult(true)
        } else {
            CheckOperationResult(false, "Payee account cannot be same account with the payer account")
        }
    }
}