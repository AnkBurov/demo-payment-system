package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.document.Document
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult

/**
 * Interface for document checking
 */
interface DocOperationChecker {

    /**
     * Check document operation
     * @param document
     * @return check result
     */
    fun checkDocOperation(document: Document): CheckOperationResult
}