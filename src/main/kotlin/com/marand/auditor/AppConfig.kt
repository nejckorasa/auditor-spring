package com.marand.auditor

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType
import org.springframework.scheduling.annotation.EnableScheduling
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import javax.jms.ConnectionFactory

/**
 * @author Nejc Korasa
 */

@Configuration
@EnableJms
@EnableSwagger2
@EnableScheduling
open class AppConfig {

    companion object { private val LOG = LoggerFactory.getLogger(AppConfig::class.toString()) }

    @Bean
    open fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.marand.auditor"))
                    .paths(PathSelectors.any())
                    .build()


    @Bean
    open fun myFactory(
            connectionFactory: ConnectionFactory,
            configurer: DefaultJmsListenerContainerFactoryConfigurer): JmsListenerContainerFactory<*> {

        val factory = DefaultJmsListenerContainerFactory().apply {
            setConcurrency("3-15")
            setErrorHandler { LOG.error("Error handling message", it) }
        }

        configurer.configure(factory, connectionFactory)
        return factory
    }

    @Bean
    open fun jacksonJmsMessageConverter(): MessageConverter = MappingJackson2MessageConverter()
            .apply {
                setTargetType(MessageType.BYTES)
                setTypeIdPropertyName("_type")
            }
}
