package com.marand.auditor

import com.marand.auditor.db.AuditInfoRepository
import com.marand.auditor.dto.AuditInfo
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component


/**
 * @author Nejc Korasa
 */

@Component
class AuditDetailsReceiver(private val auditInfoRepository: AuditInfoRepository) {

    companion object {
        private val LOG = LoggerFactory.getLogger(AuditDetailsReceiver::class.java)
    }

    @JmsListener(destination = "auditQueue", containerFactory = "myFactory")
    fun receiveMessage(auditInfo: AuditInfo) {

        LOG.info("Received [$auditInfo]")
        auditInfoRepository.save(AuditInfoMapper.fromDto(auditInfo))
    }
}
