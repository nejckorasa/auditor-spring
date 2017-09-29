package com.marand.auditer.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * @author Nejc Korasa
 */

@Component
public class AuditDetailsReceiver
{

  @JmsListener(destination = "auditQueue")
  public void receiveMessage(final AuditDetails auditDetails)
  {
    System.out.println("Received [" + auditDetails+ "]");
  }
}
