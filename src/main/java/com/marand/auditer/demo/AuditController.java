package com.marand.auditer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Nejc Korasa
 */

@RestController
@RequestMapping("/audit")
public class AuditController
{
  private final JmsTemplate jmsTemplate;

  @Autowired
  public AuditController(final JmsTemplate jmsTemplate)
  {
    this.jmsTemplate = jmsTemplate;
  }

  @PostMapping
  public void send(@RequestBody final AuditDetails auditDetails)
  {
    System.out.println("Sending a transaction.");
    jmsTemplate.convertAndSend("auditQueue", auditDetails);
  }
}
