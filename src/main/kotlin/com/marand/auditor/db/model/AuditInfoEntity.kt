package com.marand.auditor.db.model

import javax.persistence.*

/**
 * @author Nejc Korasa
 */

@Entity
@Table(name = "audit_info")
class AuditInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var sender: String? = null
    var userId: String? = null
    var userName: String? = null
    var returnResult: String? = null
    var method: String? = null
    var parameters: String? = null


    override fun toString() =
            "AuditInfoEntity(id=$id, sender=$sender, userId=$userId, userName=$userName, returnResult=$returnResult, method=$method, parameters=$parameters)"
}
