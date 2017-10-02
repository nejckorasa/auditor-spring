package com.marand.auditor

import com.marand.auditor.dto.AuditInfo
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component




/**
 * @author Nejc Korasa
 */

@Component
class AuditReceiver(private val auditService: AuditService) {

    companion object { private val LOG = LoggerFactory.getLogger(AuditReceiver::class.java) }

    @JmsListener(destination = "auditQueue", containerFactory = "myFactory")
    fun receiveMessage(auditInfo: AuditInfo) {

        LOG.info("Received [$auditInfo]")
        auditService.audit(auditInfo)
    }
}
