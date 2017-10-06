package com.marand.auditor.db;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marand.auditor.AuditRecovery;
import com.marand.auditor.AuditService;
import com.marand.auditor.db.model.AuditInfoEntity;
import com.marand.auditor.dto.AuditInfo;
import com.marand.auditor.dto.AuditInfoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nejc Korasa
 */

@Service
@Profile("db-audit")
public class DbAuditService implements AuditService
{
  private final AuditInfoRepository auditInfoRepository;
  private final AuditRecovery auditRecovery;

  public DbAuditService(final AuditInfoRepository auditInfoRepository, final AuditRecovery auditRecovery)
  {
    this.auditInfoRepository = auditInfoRepository;
    this.auditRecovery = auditRecovery;
  }

  @Override
  @Transactional
  @Retryable
  public void audit(final AuditInfo auditInfo)
  {
    auditInfoRepository.save(AuditInfoMapper.Companion.fromDto(auditInfo));
  }

  @Override
  @Transactional
  public void auditBatch(final Collection<AuditInfo> auditInfos) throws Exception
  {
    final Set<AuditInfoEntity> audits = auditInfos
        .stream()
        .map(AuditInfoMapper.Companion::fromDto)
        .collect(Collectors.toSet());

    auditInfoRepository.save(audits);
  }

  @Override
  @Recover
  public void recover(final Exception ex, final AuditInfo auditInfo) throws JsonProcessingException
  {
    auditRecovery.logFailed(auditInfo, ex);
  }
}
