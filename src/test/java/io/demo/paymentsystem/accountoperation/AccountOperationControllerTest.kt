package io.demo.paymentsystem.accountoperation

import io.demo.paymentsystem.api.accountoperation.DepositMoneyRequest
import io.demo.paymentsystem.bodyNotNull
import io.demo.paymentsystem.ok
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountOperationControllerTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun depositMoney() {
        val depositMoneyRequest = DepositMoneyRequest(BigDecimal.TEN, accountId = 1)

        restTemplate.exchange(ACCOUNT_OPERATIONS + DEPOSIT, HttpMethod.PUT, HttpEntity(depositMoneyRequest), Any::class.java)
                .ok()
    }

    @Test
    fun withdrawMoney() {
        val accountId = 1
        val amount = BigDecimal.ONE

        restTemplate.getForEntity("$ACCOUNT_OPERATIONS$WITHDRAW?accountId=$accountId&amount=$amount", BigDecimal::class.java)
                .ok()
                .bodyNotNull()
                .let { assertEquals(amount, it) }
    }

    companion object {
        const val ACCOUNT_OPERATIONS = "/account-operation"
        const val DEPOSIT = "/deposit"
        const val WITHDRAW = "/withdraw"
    }
}