package io.demo.paymentsystem.api

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class User(
        @Id
        @GeneratedValue
        val id: Long,

        val firstName: String,

        val lastName: String,

        @ManyToOne
        val org: Org) {
}