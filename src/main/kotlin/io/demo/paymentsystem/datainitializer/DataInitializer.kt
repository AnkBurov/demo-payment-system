package io.demo.paymentsystem.datainitializer

import io.demo.paymentsystem.api.Account
import io.demo.paymentsystem.api.AccountState
import io.demo.paymentsystem.api.Org
import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.api.document.Payment
import io.demo.paymentsystem.repository.account.AccountRepository
import io.demo.paymentsystem.repository.org.OrgRepository
import io.demo.paymentsystem.repository.document.PaymentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.math.BigDecimal
import javax.transaction.Transactional

/**
 * Test data initializer
 *
 * In real application there would be separate test data generator for using in integration tests
 */
@Component
class DataInitializer(
        private val orgRepository: OrgRepository,
        private val accountRepository: AccountRepository,
        private val paymentRepository: PaymentRepository
) {

    @Transactional
    fun createEntities() {
        val firstOrg = Org(name = "first org")
        orgRepository.save(firstOrg)

        val firstOrgAccount = Account(accountNumber = "40810000000001234567", accountState = AccountState.OPEN, org = firstOrg, balance = BigDecimal(1000), currencyCode = 810)
        accountRepository.save(firstOrgAccount)

        val secondOrg = Org(name = "second org")
        orgRepository.save(secondOrg)

        val secondOrgAccount = Account(accountNumber = "40810000000001234568", accountState = AccountState.OPEN, org = secondOrg, balance = BigDecimal(1000), currencyCode = 810)
        accountRepository.save(secondOrgAccount)

        val payment = Payment(payer = firstOrgAccount,
                payee = secondOrgAccount,
                amount = BigDecimal.ONE,
                currencyCode = 810,
                docStatus = DocStatus.CREATED)
        paymentRepository.save(payment)

        LOG.info("Entities created")
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(this::class.java)
    }
}