package com.marand.auditor;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * @author Nejc Korasa
 */

@Component
public class AuditDetailsReceiver
{
  private static final Logger LOG = LoggerFactory.getLogger(AuditDetailsReceiver.class);

  @JmsListener(destination = "auditQueue", containerFactory = "myFactory")
  public void receiveMessage(final AuditInfo auditInfo)
  {
    LOG.info("Received [" + auditInfo + "]");
  }
}
