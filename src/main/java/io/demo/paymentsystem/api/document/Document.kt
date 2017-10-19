package io.demo.paymentsystem.api.document

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Document(
        @Id
        @GeneratedValue
        val id: Long? = null,

        @Version
        val version: Long? = null,

        @Enumerated(EnumType.STRING)
        val docType: DocType,

        @Enumerated(EnumType.STRING)
        var docStatus: DocStatus,

        val description: String? = null
) {
}