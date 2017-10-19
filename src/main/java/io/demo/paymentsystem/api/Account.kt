package io.demo.paymentsystem.api

import org.hibernate.annotations.Check
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.DecimalMin

@Entity
@Check(constraints = "BALANCE >= 0")
class Account(
        @Id
        @GeneratedValue
        val id: Long? = null,

        @Version
        val version: Long? = null,

        val accountNumber: String,

        @Enumerated(EnumType.STRING)
        val accountState: AccountState,

        @ManyToOne
        val org: Org,

        @DecimalMin(value = "0")
        var balance: BigDecimal,

        val currencyCode: Int
) {
}