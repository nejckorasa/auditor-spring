package com.marand.auditor.dto

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.marand.auditor.db.model.AuditInfoEntity
import org.springframework.stereotype.Component

/**
 * @author Nejc Korasa
 */

@Component
open class AuditInfoMapper(private val objectMapper: ObjectMapper) {

    open fun fromDto(auditInfo: AuditInfo): AuditInfoEntity = AuditInfoEntity().apply {

        method = auditInfo.method
        sender = auditInfo.sender
        userName = auditInfo.user
        executionTime = auditInfo.executionTime
        arguments = objectMapper.writeValueAsString(auditInfo.arguments)
        requestTime = auditInfo.requestTime
        result = auditInfo.result
        traceId = auditInfo.traceId
        requestId = auditInfo.requestId
    }


    open fun toDto(auditInfoEntity: AuditInfoEntity): AuditInfo = AuditInfo(
            auditInfoEntity.sender,
            auditInfoEntity.traceId,
            auditInfoEntity.requestId,
            auditInfoEntity.requestTime,
            auditInfoEntity.method,
            objectMapper.readValue(auditInfoEntity.arguments, object : TypeReference<Map<String, Any>>() {}),
            auditInfoEntity.result,
            auditInfoEntity.executionTime,
            auditInfoEntity.userName)
}