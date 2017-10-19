package io.demo.paymentsystem.service.payment

import io.demo.paymentsystem.api.PaymentCreationRequest
import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.api.document.PaymentDto
import io.demo.paymentsystem.api.document.Payment
import io.demo.paymentsystem.api.document.toPaymentDto
import io.demo.paymentsystem.api.toPayment
import io.demo.paymentsystem.repository.document.PaymentRepository
import io.demo.paymentsystem.service.account.AccountService
import io.demo.paymentsystem.service.lifecycle.DocLifeCycleService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class PaymentServiceImpl(
        private val accountService: AccountService,
        private val paymentRepository: PaymentRepository,
        private val docTypeLifeCycleService: DocLifeCycleService
) : PaymentService {
    @Transactional
    override fun createPayment(creationRequest: PaymentCreationRequest): PaymentDto {
        val payerAccount = accountService.getAccount(creationRequest.payerAccountId)
        val payeeAccount = accountService.getAccount(creationRequest.payeeAccountNumber)
        val payment = creationRequest.toPayment(payerAccount, payeeAccount, DocStatus.INITIAL)

        return docTypeLifeCycleService.changeDocStatus(payment, DocStatus.CREATED) {
            paymentRepository.save(it)
                    .toPaymentDto()
        }
    }

    @Transactional
    override fun approveCreatedPayments(): List<Payment> {
        val payments = paymentRepository.findByDocStatus(DocStatus.CREATED)
        for (payment in payments) {
            try {
                docTypeLifeCycleService.changeDocStatus(payment, DocStatus.CONFIRMED) { executeTransaction(it)}
            } catch (e: Exception) {
                LOG.warn("Declining payment because of: ", e)
                docTypeLifeCycleService.changeDocStatus(payment, DocStatus.DECLINED) { declineTransaction(it)}
            }
        }
        return payments
    }

    private fun executeTransaction(payment: Payment) {
        with(payment) {
            val subtractedMoney = accountService.subtractMoney(payer, amount, currencyCode)
            accountService.addMoney(payee, subtractedMoney, currencyCode)
            payment.docStatus = DocStatus.CONFIRMED
            paymentRepository.save(payment)
        }
    }

    private fun declineTransaction(payment: Payment) {
        payment.docStatus = DocStatus.DECLINED
        paymentRepository.save(payment)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(this::class.java)
    }
}