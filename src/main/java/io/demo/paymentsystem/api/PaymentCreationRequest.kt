package io.demo.paymentsystem.api

import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.api.document.Payment
import org.hibernate.validator.constraints.NotBlank
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin

data class PaymentCreationRequest(
        val payerAccountId: Long,

        @get: DecimalMin(value = "0", inclusive = false)
        val amount: BigDecimal,

        val currencyCode: Int? = null,

        @get: NotBlank
        val payeeAccountNumber: String,

        val description: String? = null
) {
}

fun PaymentCreationRequest.toPayment(payerAccount: Account, payeeAccount: Account, docStatus: DocStatus): Payment {
        val currencyCode = this.currencyCode ?: payerAccount.currencyCode
        return Payment(description = this.description,
                payer = payerAccount,
                payee = payeeAccount,
                amount = this.amount,
                currencyCode = currencyCode,
                docStatus = docStatus)
}