package io.demo.paymentsystem.service.account

import io.demo.paymentsystem.api.Account
import java.math.BigDecimal

/**
 * Service for accounts
 */
interface AccountService {

    /**
     * Get account by id
     */
    fun getAccount(id: Long): Account

    /**
     * Get account by account number
     */
    fun getAccount(accountNumber: String): Account

    /**
     * Subtract money from account
     * @return subtracted money
     */
    fun subtractMoney(account: Account, amount: BigDecimal, currencyCode: Int): BigDecimal

    /**
     * Add money to account
     */
    fun addMoney(account: Account, amount: BigDecimal, currencyCode: Int)
}