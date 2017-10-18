package io.demo.paymentsystem.service.accountoperations

import io.demo.paymentsystem.api.Account
import io.demo.paymentsystem.api.accountoperation.DepositMoneyRequest
import io.demo.paymentsystem.api.accountoperation.toAccountOperation
import io.demo.paymentsystem.api.document.AccountOperation
import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.repository.document.AccountOperationRepository
import io.demo.paymentsystem.service.account.AccountService
import io.demo.paymentsystem.service.lifecycle.DocLifeCycleService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.transaction.Transactional

@Service
class AccountOperationsServiceImpl(
        private val accountService: AccountService,
        private val accountOperationRepository: AccountOperationRepository,
        private val docTypeLifeCycleService: DocLifeCycleService
) : AccountOperationsService {

    @Transactional
    override fun depositMoney(depositMoneyRequest: DepositMoneyRequest) {
        val account = accountService.getAccount(depositMoneyRequest.accountId)
        val accountOperation = depositMoneyRequest.toAccountOperation(account, DocStatus.INITIAL)

        docTypeLifeCycleService.changeDocStatus(accountOperation, DocStatus.CONFIRMED) {
            accountOperationRepository.save(it)
            accountService.addMoney(it.account, it.amount, account.currencyCode)
        }
    }

    @Transactional
    override fun withdrawMoney(accountId: Long, amount: BigDecimal): BigDecimal {
        val account = accountService.getAccount(accountId)
        val accountOperation = createAccountOperation(account, amount, AccountOperation.AccountOperationType.WITHDRAW, DocStatus.INITIAL)

        return docTypeLifeCycleService.changeDocStatus(accountOperation, DocStatus.CONFIRMED) {
            accountOperationRepository.save(it)
            accountService.subtractMoney(it.account, it.amount, account.currencyCode)
        }
    }

    companion object {
        private fun createAccountOperation(account: Account, amount: BigDecimal,
                                        accountOperationType: AccountOperation.AccountOperationType, docStatus: DocStatus): AccountOperation {
            return AccountOperation(account = account, amount = amount,
                    accountOperationType = accountOperationType, docStatus = docStatus)
        }
    }
}