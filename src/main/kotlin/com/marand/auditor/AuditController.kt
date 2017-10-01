package com.marand.auditor

import com.marand.auditor.dto.AuditInfo
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


/**
 * @author Nejc Korasa
 */

@RestController
@RequestMapping("/audit")
open class AuditController @Autowired
constructor(private val jmsTemplate: JmsTemplate) {

    @PostMapping
    @ApiOperation(value = "Post Audit Info")
    open fun send(@ApiParam(name = "auditInfo", required = true) @Valid @RequestBody auditInfo: AuditInfo) {
        jmsTemplate.convertAndSend("auditQueue", auditInfo)
    }
}
