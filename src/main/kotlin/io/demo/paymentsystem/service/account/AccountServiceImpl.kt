package io.demo.paymentsystem.service.account

import io.demo.paymentsystem.api.Account
import io.demo.paymentsystem.repository.account.AccountRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AccountServiceImpl(private val accountRepository: AccountRepository) : AccountService {

    override fun getAccount(id: Long): Account {
        // in real application there would be additional criteria by user's org id to prevent security issues
        return accountRepository.findOne(id) ?: throw IllegalArgumentException("Account with id $id is not found")
    }

    override fun getAccount(accountNumber: String): Account {
        return accountRepository.findByAccountNumber(accountNumber) ?: throw IllegalArgumentException("Account with id $accountNumber is not found")
    }

    override fun subtractMoney(account: Account, amount: BigDecimal, currencyCode: Int): BigDecimal {
        account.balance -= amount
        if (account.balance < BigDecimal.ZERO) throw IllegalArgumentException("Balance cannot be negative number")
        accountRepository.save(account)
        return amount
    }

    override fun addMoney(account: Account, amount: BigDecimal, currencyCode: Int) {
        account.balance += amount
        accountRepository.save(account)
    }
}
