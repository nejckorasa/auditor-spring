package com.marand.auditor;

import java.util.Collection;

import com.marand.auditor.dto.AuditInfo;
import org.springframework.stereotype.Service;

/**
 * @author Nejc Korasa
 */

@SuppressWarnings("unused")
@Service
public interface AuditService
{
  void audit(final AuditInfo auditInfo) throws Exception;

  void recover(Exception ex, AuditInfo auditInfo) throws Exception;

  void auditBatch(final Collection<AuditInfo> auditInfos) throws Exception;

  void recoverBatch(Exception ex, Collection<AuditInfo> auditInfos) throws Exception;
}
