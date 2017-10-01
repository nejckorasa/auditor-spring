package com.marand.auditor

import com.marand.auditor.db.model.AuditInfoEntity
import com.marand.auditor.dto.AuditInfo

/**
 * @author Nejc Korasa
 */

open class AuditInfoMapper {

    companion object {

        fun fromDto(auditInfo: AuditInfo): AuditInfoEntity = AuditInfoEntity().apply {

            method = auditInfo.method
            sender = auditInfo.sender
            userName = auditInfo.user
            userId = auditInfo.userId
            parameters = auditInfo.parameters
            returnResult = auditInfo.returnResult
        }


        fun toDto(auditInfoEntity: AuditInfoEntity): AuditInfo = AuditInfo().apply {

            method = auditInfoEntity.method
            sender = auditInfoEntity.sender
            user = auditInfoEntity.userName
            userId = auditInfoEntity.userId
            parameters = auditInfoEntity.parameters
            returnResult = auditInfoEntity.returnResult
        }
    }
}