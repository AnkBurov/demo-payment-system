package io.demo.paymentsystem.service.accountoperations

import io.demo.paymentsystem.api.accountoperation.DepositMoneyRequest
import java.math.BigDecimal

/**
 * Service for account operations
 */
interface AccountOperationsService {

    /**
     * Deposit money to account
     *
     * @param depositMoneyRequest deposit request
     */
    fun depositMoney(depositMoneyRequest: DepositMoneyRequest)

    /**
     * Withdraw money from account
     *
     * @param accountId account id
     * @param amount amount of withdrawal money
     * @return withdrawn money
     */
    fun withdrawMoney(accountId: Long, amount: BigDecimal): BigDecimal
}