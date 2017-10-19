package io.demo.paymentsystem.repository.document

import io.demo.paymentsystem.api.document.AccountOperation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountOperationRepository : JpaRepository<AccountOperation, Long> {
}