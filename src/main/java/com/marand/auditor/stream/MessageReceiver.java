package com.marand.auditor.stream;

import com.marand.auditor.dto.AuditInfo;
import com.marand.auditor.saver.AuditService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Nejc Korasa
 */

@Profile("stream")
@Configuration
@EnableBinding(Sink.class)
public class MessageReceiver
{
  private final AuditService auditService;

  public MessageReceiver(final AuditService auditService)
  {
    this.auditService = auditService;
  }

  @StreamListener(Sink.INPUT)
  public void handle(final AuditInfo auditInfo) throws Exception
  {
    auditService.audit(auditInfo);
  }
}
