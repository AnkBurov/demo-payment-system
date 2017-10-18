package io.demo.paymentsystem.service.lifecycle

import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.api.document.Document
import io.demo.paymentsystem.api.lifecycle.check.CheckOperationResult
import io.demo.paymentsystem.api.lifecycle.check.DocOperationCheckResult
import io.demo.paymentsystem.service.lifecycle.check.DocCheckerStorage
import org.springframework.stereotype.Service

@Service
class DocLifeCycleServiceImpl(private val docCheckerStorage: DocCheckerStorage) : DocLifeCycleService {
    override fun isOperationAccepted(document: Document, newStatus: DocStatus): DocOperationCheckResult {
        val docOperationCheckers = docCheckerStorage.getDocOperationCheckers(document.docType, newStatus)
        val checkersResult = arrayListOf<CheckOperationResult>()
        docOperationCheckers.mapTo(checkersResult) { it.checkDocOperation(document) }
        return if (checkersResult.any { !it.result }) {
            val errorResult = DocOperationCheckResult(false)
            checkersResult.filter { !it.result }
                    .forEach { errorResult.errors.add(it.errorMessage) }
            errorResult
        } else {
            DocOperationCheckResult(true)
        }
    }

    override fun <T : Document, R> changeDocStatus(document: T, newStatus: DocStatus, action: (T) -> R): R {
        val operationAcceptedResult = isOperationAccepted(document, newStatus)
        if (!operationAcceptedResult.errors.isEmpty()) throw IllegalArgumentException(operationAcceptedResult.errors.joinToString())
        document.docStatus = newStatus
        return action(document)
    }
}