package com.marand.auditor;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marand.auditor.db.AuditInfoRepository;
import com.marand.auditor.dto.AuditInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nejc Korasa
 */

@Service
public class AuditService
{
  private static final Logger LOG = LoggerFactory.getLogger(AuditService.class);

  private final AuditInfoRepository auditInfoRepository;
  private final AuditRecovery auditRecovery;
  private final ObjectMapper objectMapper;

  public AuditService(
      final AuditInfoRepository auditInfoRepository,
      final AuditRecovery auditRecovery,
      final ObjectMapper objectMapper)
  {
    this.auditInfoRepository = auditInfoRepository;
    this.auditRecovery = auditRecovery;
    this.objectMapper = objectMapper;
  }

  @Transactional
  @Async
  @Retryable(
      maxAttempts = 5,
      backoff = @Backoff(5000))
  public void audit(final AuditInfo auditInfo)
  {
    auditInfoRepository.save(AuditInfoMapper.Companion.fromDto(auditInfo));
  }

  @SuppressWarnings("unused")
  @Recover
  public void recover(final Exception ex, final AuditInfo auditInfo) throws JsonProcessingException
  {
    auditRecovery.logFailed(auditInfo);
    LOG.error("SAVING FAILED [object, error] :"
                  + objectMapper.writeValueAsString(auditInfo) + ", "
                  + Arrays.toString(ex.getStackTrace()));
  }
}
