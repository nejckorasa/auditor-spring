package com.marand.auditor

import com.fasterxml.jackson.databind.ObjectMapper
import com.marand.auditor.dto.AuditInfo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*

/**
 * @author Nejc Korasa
 */

private const val FAILED_AUDITS_FILE = "failed-audits.txt"

@Component
open class AuditRecovery(private var objectMapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(AuditRecovery::class.java)

    open fun logFailed(auditInfo: AuditInfo, ex: Exception?) {

        Files.newBufferedWriter(Paths.get(FAILED_AUDITS_FILE), StandardOpenOption.APPEND, StandardOpenOption.CREATE)
                .use({
                    it.append(objectMapper.writeValueAsString(auditInfo))
                    it.newLine()
                })

        logger.error("SAVING FAILED [object, error] :" +
                "${objectMapper.writeValueAsString(auditInfo)}, ${ex?.let { Arrays.toString(it.stackTrace) }}")
    }

}