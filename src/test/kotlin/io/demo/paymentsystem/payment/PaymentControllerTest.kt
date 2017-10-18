package io.demo.paymentsystem.payment

import io.demo.paymentsystem.api.PaymentCreationRequest
import io.demo.paymentsystem.api.document.PaymentDto
import io.demo.paymentsystem.badRequest
import io.demo.paymentsystem.bodyNotNull
import io.demo.paymentsystem.ok
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun createPayment() {
        val creationRequest = PaymentCreationRequest(1, BigDecimal.ONE, payeeAccountNumber = "40810000000001234568")

        restTemplate.postForEntity(PAYMENT, creationRequest, PaymentDto::class.java)
                .ok()
                .bodyNotNull()
                .let { Assert.assertNotNull("Created payment has no id", it.id) }
    }

    @Test
    fun createPaymentZeroAmount() {
        val creationRequest = PaymentCreationRequest(1, BigDecimal.ZERO, payeeAccountNumber = "40810000000001234567")

        restTemplate.postForEntity(PAYMENT, creationRequest, Any::class.java)
                .badRequest()
    }

    @Test
    fun createPaymentToSameAccount() {
        val creationRequest = PaymentCreationRequest(1, BigDecimal.ONE, payeeAccountNumber = "40810000000001234567")

        restTemplate.postForEntity(PAYMENT, creationRequest, Any::class.java)
                .badRequest()
    }

    companion object {
        const val PAYMENT = "/payment"
    }
}