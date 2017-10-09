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
  private final AuditInfoMapper auditInfoMapper;

  public DbAuditService(
      final AuditInfoRepository auditInfoRepository,
      final AuditRecovery auditRecovery,
      final AuditInfoMapper auditInfoMapper)
  {
    this.auditInfoRepository = auditInfoRepository;
    this.auditRecovery = auditRecovery;
    this.auditInfoMapper = auditInfoMapper;
  }

  @Override
  @Transactional
  @Retryable
  public void audit(final AuditInfo auditInfo)
  {
    auditInfoRepository.save(auditInfoMapper.fromDto(auditInfo));
  }

  @Override
  @Transactional
  public void audit(final Collection<AuditInfo> auditInfos) throws Exception
  {
    final Set<AuditInfoEntity> audits = auditInfos
        .stream()
        .map(auditInfoMapper::fromDto)
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
