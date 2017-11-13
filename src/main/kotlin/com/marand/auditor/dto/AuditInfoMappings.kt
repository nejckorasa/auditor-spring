package com.marand.auditor.dto

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.marand.auditor.db.model.AuditInfoEntity

/**
 * @author Nejc Korasa
 */

fun AuditInfo.toEntity(objectMapper: ObjectMapper): AuditInfoEntity = AuditInfoEntity().apply {

    method = method
    sender = sender
    userName = user
    executionTime = executionTime
    arguments = objectMapper.writeValueAsString(arguments)
    requestTime = requestTime
    result = result
    traceId = traceId
    requestId = requestId
    errorMessage = error
}

fun AuditInfoEntity.toDto(objectMapper: ObjectMapper): AuditInfo = AuditInfo(

        sender,
        traceId,
        requestId,
        requestTime,
        method,
        objectMapper.readValue(arguments, object : TypeReference<Map<String, Any>>() {}),
        result,
        executionTime,
        errorMessage,
        userName)
