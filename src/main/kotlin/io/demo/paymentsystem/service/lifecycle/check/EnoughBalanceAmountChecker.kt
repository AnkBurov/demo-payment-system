package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.document.Payment
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult
import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * Check that payment account has enough money for payment
 */
@Component
class EnoughBalanceAmountChecker : AbstractPaymentChecker() {

    override fun checkDocOperation(payment: Payment): CheckOperationResult {
        val comparingAmount = if (payment.currencyCode == payment.payer.currencyCode) {
            payment.amount
        } else {
            currencyConverter(payment.amount, payment.currencyCode, payment.payer.currencyCode)
        }

        return if (comparingAmount <= payment.payer.balance) {
            CheckOperationResult(true)
        } else {
            CheckOperationResult(false, "Account ${payment.payer.accountNumber} doesn't have enough money for this operation")
        }
    }

    private fun currencyConverter(amount: BigDecimal, fromCurrencyCode: Int, toCurrencyCode: Int): BigDecimal {
        return amount //mock
    }
}