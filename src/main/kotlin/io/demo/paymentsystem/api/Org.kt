package io.demo.paymentsystem.api

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Org(
        @Id
        @GeneratedValue
        val id: Long? = null,

        val name: String)  {
}