package io.demo.paymentsystem.controller

import io.demo.paymentsystem.api.accountoperation.DepositMoneyRequest
import io.demo.paymentsystem.service.accountoperations.AccountOperationsService
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

/**
 * Rest controller for account operations
 */
@RestController
@RequestMapping("/account-operation")
class AccountOperationsController(private val accountOperationsService: AccountOperationsService) {

    /**
     * Deposit money to account
     */
    @RequestMapping(value = "/deposit", method = arrayOf(RequestMethod.PUT))
    fun depositMoney(@RequestBody depositMoneyRequest: DepositMoneyRequest) {
        accountOperationsService.depositMoney(depositMoneyRequest)
    }

    /**
     * Withdraw money from account
     */
    @RequestMapping(value = "/withdraw", method = arrayOf(RequestMethod.GET))
    fun withdrawMoney(@RequestParam accountId: Long, @RequestParam amount: BigDecimal): BigDecimal {
        return accountOperationsService.withdrawMoney(accountId, amount)
    }
}