package com.marand.auditor.dto

import org.hibernate.validator.constraints.NotBlank

/**
 * @author Nejc Korasa
 */

class AuditInfo {

    var sender: String? = null
    @NotBlank var userId: String? = null
    @NotBlank var user: String? = null
    var returnResult: String? = null
    var method: String? = null
    var parameters: String? = null


    override fun toString() =
            "AuditInfo(sender=$sender, userId=$userId, user=$user, returnResult=$returnResult, method=$method, parameters=$parameters)"

}
