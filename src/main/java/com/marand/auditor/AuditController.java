package com.marand.auditor;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
  @ApiOperation(value = "Post Audit Info")
  public void send(@ApiParam(name = "auditInfo", required = true) @Valid @RequestBody final AuditInfo auditInfo)
  {
    jmsTemplate.convertAndSend("auditQueue", auditInfo);
  }
}
