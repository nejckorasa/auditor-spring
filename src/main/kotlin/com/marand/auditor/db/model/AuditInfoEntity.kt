package com.marand.auditor.db.model

import java.util.*
import javax.persistence.*

/**
 * @author Nejc Korasa
 */

@Entity
@Table(name = "audit_info")
data class AuditInfoEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @Column(nullable = false)
        var sender: String? = null,

        @Column(nullable = false)
        var traceId: String? = null,

        @Column(nullable = false)
        var requestId: String? = null,

        @Column(nullable = false)
        var requestTime: Date? = null,

        @Column(nullable = false)
        var method: String? = null,

        var arguments: String? = null,
        var result: String? = null,

        @Column(nullable = false)
        var userId: String? = null,

        @Column(nullable = false)
        var userName: String? = null)
