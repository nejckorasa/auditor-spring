package com.marand.auditor

import com.fasterxml.jackson.databind.ObjectMapper
import com.marand.auditor.dto.AuditInfo
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * @author Nejc Korasa
 */

private const val FAILED_AUDITS_FILE = "failed-audits.txt"

@Component
open class AuditRecovery(private var objectMapper: ObjectMapper) {

    open fun logFailed(auditInfo: AuditInfo) = Files
            .newBufferedWriter(Paths.get(FAILED_AUDITS_FILE), StandardOpenOption.APPEND, StandardOpenOption.CREATE)
            .use({
                it.append(objectMapper.writeValueAsString(auditInfo))
                it.newLine()
            })

}