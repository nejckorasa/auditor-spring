package com.marand.auditor.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType
import org.springframework.retry.annotation.EnableRetry
import org.springframework.scheduling.annotation.EnableAsync
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * @author Nejc Korasa
 */

@Configuration
@EnableJms
@EnableSwagger2
@EnableRetry
@EnableAsync
open class AppConfig {

    @Bean
    open fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.marand.auditor"))
                    .paths(PathSelectors.any())
                    .build()

    @Bean
    open fun jacksonJmsMessageConverter(): MessageConverter = MappingJackson2MessageConverter()
            .apply {
                setTargetType(MessageType.BYTES)
                setTypeIdPropertyName("_type")
            }
}
