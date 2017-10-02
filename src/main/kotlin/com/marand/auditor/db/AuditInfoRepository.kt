package com.marand.auditor.db

import com.marand.auditor.db.model.AuditInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Nejc Korasa
 */

interface AuditInfoRepository : JpaRepository<AuditInfoEntity, Long>
