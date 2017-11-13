package com.marand.auditor

import com.fasterxml.jackson.databind.ObjectMapper
import com.marand.auditor.dto.AuditInfo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author Nejc Korasa
 */

@Component
open class AuditRecovery(private var objectMapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(AuditRecovery::class.java)
    private val failedLogger = LoggerFactory.getLogger("failed-log")

    open fun logFailed(auditInfo: AuditInfo, ex: Exception?) {

        val json = objectMapper.writeValueAsString(auditInfo)

        failedLogger.info(json)
        logger.error("SAVING FAILED [object, error] :" + "$json, ${ex?.let { Arrays.toString(it.stackTrace) }}")
    }
}