package com.marand.auditor.file;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marand.auditor.AuditRecovery;
import com.marand.auditor.AuditService;
import com.marand.auditor.dto.AuditInfo;
import com.marand.auditor.dto.AuditInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Nejc Korasa
 */

@Service
@Profile("file-audit")
public class FileAuditService implements AuditService
{
  private static final Logger AUDIT_LOG = LoggerFactory.getLogger("audit-log");

  private final AuditRecovery auditRecovery;
  private final ObjectMapper objectMapper;

  public FileAuditService(final AuditRecovery auditRecovery, final ObjectMapper objectMapper)
  {
    this.auditRecovery = auditRecovery;
    this.objectMapper = objectMapper;
  }

  @Override
  @Async
  @Retryable
  public void audit(final AuditInfo auditInfo) throws JsonProcessingException
  {
    AUDIT_LOG.info(objectMapper.writeValueAsString(AuditInfoMapper.Companion.fromDto(auditInfo)));
  }

  @Override
  public void auditBatch(final Collection<AuditInfo> auditInfos) throws Exception
  {
    for (final AuditInfo auditInfo : auditInfos)
    {
      AUDIT_LOG.info(objectMapper.writeValueAsString(AuditInfoMapper.Companion.fromDto(auditInfo)));
    }
  }

  @Override
  @Recover
  public void recover(final Exception ex, final AuditInfo auditInfo) throws JsonProcessingException
  {
    auditRecovery.logFailed(auditInfo, ex);
  }
}
