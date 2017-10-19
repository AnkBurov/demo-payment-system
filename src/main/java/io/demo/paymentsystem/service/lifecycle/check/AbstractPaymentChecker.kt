package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.document.Document
import io.demo.paymentsystem.api.document.Payment
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult

abstract class AbstractPaymentChecker : DocOperationChecker {

    override fun checkDocOperation(document: Document): CheckOperationResult {
        if (document !is Payment) throw IllegalArgumentException("Checking document is not a payment document")
        return checkDocOperation(document)
    }

    protected abstract fun checkDocOperation(payment: Payment): CheckOperationResult
}