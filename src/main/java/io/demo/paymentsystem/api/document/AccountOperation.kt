package io.demo.paymentsystem.api.document

import io.demo.paymentsystem.api.Account
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

@Entity
class AccountOperation(
        id: Long? = null,

        version: Long? = null,

       docStatus: DocStatus,

        description: String? = null,

        @ManyToOne
        val account: Account,

        val amount: BigDecimal,

        @Enumerated(EnumType.STRING)
        val accountOperationType: AccountOperationType
) : Document(id, version, DocType.ACCOUNT_OPERATION, docStatus, description) {

    enum class AccountOperationType {
        DEPOSIT,
        WITHDRAW
    }
}