package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.AccountState
import io.demo.paymentsystem.api.document.Payment
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult
import org.springframework.stereotype.Component

/**
 * Check that payer and payee accounts are in open state
 */
@Component
class OpenAccountsChecker : AbstractPaymentChecker(){

    override fun checkDocOperation(payment: Payment): CheckOperationResult {
        return when {
            payment.payer.accountState != AccountState.OPEN -> CheckOperationResult(false, "Payer account is not in an open state")
            payment.payee.accountState != AccountState.OPEN -> CheckOperationResult(false, "Payee account is not in an open state")
            else -> CheckOperationResult(true)
        }
    }
}