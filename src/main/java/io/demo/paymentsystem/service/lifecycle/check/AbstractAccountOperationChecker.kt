package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.document.AccountOperation
import io.demo.paymentsystem.api.document.Document
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult

abstract class AbstractAccountOperationChecker : DocOperationChecker {

    override fun checkDocOperation(document: Document): CheckOperationResult {
        if (document !is AccountOperation) throw IllegalArgumentException("Checking document is not a account operation document")
        return checkDocOperation(document)
    }

    protected abstract fun checkDocOperation(accountOperation: AccountOperation): CheckOperationResult
}