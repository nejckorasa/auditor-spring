package com.marand.auditor.db

import com.marand.auditor.db.model.AuditInfoEntity
import org.springframework.data.repository.CrudRepository

/**
 * @author Nejc Korasa
 */

interface AuditInfoRepository : CrudRepository<AuditInfoEntity, Long>
