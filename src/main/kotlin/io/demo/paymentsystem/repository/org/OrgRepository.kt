package io.demo.paymentsystem.repository.org

import io.demo.paymentsystem.api.Account
import io.demo.paymentsystem.api.Org
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrgRepository : JpaRepository<Org, Long> {
}