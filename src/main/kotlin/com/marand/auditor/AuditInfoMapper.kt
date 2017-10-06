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
            arguments = auditInfo.arguments
            requestTime = auditInfo.requestTime
            result = auditInfo.result
            traceId = auditInfo.traceId
            requestId = auditInfo.requestId
        }


        fun toDto(auditInfoEntity: AuditInfoEntity): AuditInfo = AuditInfo(
                auditInfoEntity.sender,
                auditInfoEntity.traceId,
                auditInfoEntity.requestId,
                auditInfoEntity.requestTime,
                auditInfoEntity.method,
                auditInfoEntity.arguments,
                auditInfoEntity.result,
                auditInfoEntity.userId,
                auditInfoEntity.userName)
    }
}