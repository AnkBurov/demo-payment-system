package io.demo.paymentsystem.api.accountoperation

import io.demo.paymentsystem.api.Account
import io.demo.paymentsystem.api.document.AccountOperation
import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.api.document.DocType
import java.math.BigDecimal

class DepositMoneyRequest(
        val amount: BigDecimal,

        val accountId: Long
) {
}

fun DepositMoneyRequest.toAccountOperation(account: Account, docStatus: DocStatus): AccountOperation {
    return AccountOperation(account = account,
            amount = this.amount,
            accountOperationType = AccountOperation.AccountOperationType.DEPOSIT,
            docStatus = docStatus)
}