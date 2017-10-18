package io.demo.paymentsystem.bankmock.job

import io.demo.paymentsystem.api.document.DocStatus
import io.demo.paymentsystem.api.document.Payment
import io.demo.paymentsystem.service.payment.PaymentService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Mocking of bank systems
 *
 * Every 10 second approves all payments with CREATED status
 */
@Component
class BankApprovalJob(private val paymentService: PaymentService) {

    @Scheduled(fixedDelay = 10000)
    fun approveAllCreatedPayments() {
        LOG.info("Approval job task started")
        val handledPayments = paymentService.approveCreatedPayments()
        val approvedPaymentsCount = handledPayments.getPaymentsCountByDocStatus(DocStatus.CONFIRMED)
        val declinedPaymentsCount = handledPayments.getPaymentsCountByDocStatus(DocStatus.DECLINED)
        if (approvedPaymentsCount != 0) LOG.info("$approvedPaymentsCount payments were confirmed")
        if (declinedPaymentsCount != 0) LOG.info("$declinedPaymentsCount payments were declined")
        LOG.info("Approval job task ended")
    }

    private fun List<Payment>.getPaymentsCountByDocStatus(docStatus: DocStatus): Int {
        return this.filter { it.docStatus == docStatus }
                .count()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(this::class.java)
    }
}