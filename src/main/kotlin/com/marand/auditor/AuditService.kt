package com.marand.auditor

import com.marand.auditor.db.AuditInfoRepository
import com.marand.auditor.db.model.AuditInfoEntity
import com.marand.auditor.dto.AuditInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Nejc Korasa
 */

@Service
open class AuditService(private val auditInfoRepository: AuditInfoRepository) {

    @Transactional
    open fun audit(auditInfo: AuditInfo): AuditInfoEntity = auditInfoRepository.save(AuditInfoMapper.fromDto(auditInfo))
}