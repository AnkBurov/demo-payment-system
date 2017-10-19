package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.AccountState
import io.demo.paymentsystem.api.document.AccountOperation
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult
import org.springframework.stereotype.Component

/**
 * Check that account in open state
 */
@Component
class OpenAccountChecker : AbstractAccountOperationChecker() {

    override fun checkDocOperation(accountOperation: AccountOperation): CheckOperationResult {
        return when (accountOperation.account.accountState) {
            AccountState.OPEN -> CheckOperationResult(true)
            else -> CheckOperationResult(false, "Account is not in an open state")
        }
    }
}