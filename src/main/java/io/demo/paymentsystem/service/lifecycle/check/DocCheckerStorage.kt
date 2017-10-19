package io.demo.paymentsystem.service.lifecycle.check

import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.api.document.DocType
import org.springframework.stereotype.Component

/**
 * In-memory storage for doc operation checkers
 */
@Component
class DocCheckerStorage(
        openStateCheck: OpenAccountsChecker,
        enoughBalanceAmountChecker: EnoughBalanceAmountChecker,
        notTheSameAccountChecker: NotTheSameAccountChecker,
        openAccountChecker: OpenAccountChecker,
        withdrawEnoughMoney: WithdrawEnoughMoney
) {

    private val docCheckersMap = hashMapOf<DocCheckerMapKey, List<DocOperationChecker>>()

    init {
        val paymentCheckers = listOf(openStateCheck, enoughBalanceAmountChecker, notTheSameAccountChecker)
        docCheckersMap.put(DocCheckerMapKey(DocType.PAYMENT, DocStatus.CREATED), paymentCheckers)
        docCheckersMap.put(DocCheckerMapKey(DocType.PAYMENT, DocStatus.CONFIRMED), paymentCheckers)

        val accountOperationsCheckers = listOf(openAccountChecker, withdrawEnoughMoney)
        docCheckersMap.put(DocCheckerMapKey(DocType.ACCOUNT_OPERATION, DocStatus.CONFIRMED), accountOperationsCheckers)
    }

    /**
     * Get list of document operation checkers for passed doctype and status
     */
    fun getDocOperationCheckers(docType: DocType, docStatus: DocStatus): List<DocOperationChecker> {
        return docCheckersMap.get(DocCheckerMapKey(docType, docStatus)) ?: emptyList()
    }

    private data class DocCheckerMapKey(val docType: DocType, val docStatus: DocStatus)
}