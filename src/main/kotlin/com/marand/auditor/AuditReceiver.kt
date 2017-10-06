package com.marand.auditor

import com.marand.auditor.dto.AuditInfo
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component


/**
 * @author Nejc Korasa
 */

@Component
class AuditReceiver(private val auditService: AuditService) {

    @JmsListener(destination = "auditQueue")
    fun receiveMessage(auditInfo: AuditInfo) = auditService.audit(auditInfo)
}
