package io.demo.paymentsystem.repository.account

import io.demo.paymentsystem.api.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    fun findByAccountNumber(accountNumber: String): Account?
}