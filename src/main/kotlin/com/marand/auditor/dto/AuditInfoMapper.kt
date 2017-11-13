package com.marand.auditor.dto

import com.fasterxml.jackson.databind.ObjectMapper
import com.marand.auditor.db.model.AuditInfoEntity
import org.springframework.stereotype.Component

/**
 * @author Nejc Korasa
 */

@Component
open class AuditInfoMapper(private val objectMapper: ObjectMapper) {

    open fun fromDto(auditInfo: AuditInfo): AuditInfoEntity = auditInfo.toEntity(objectMapper)

    open fun toDto(auditInfoEntity: AuditInfoEntity): AuditInfo = auditInfoEntity.toDto(objectMapper)
}