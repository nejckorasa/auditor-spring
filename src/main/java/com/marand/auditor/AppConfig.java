package com.marand.auditor;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Nejc Korasa
 */

@Configuration
@EnableJms
@EnableSwagger2
@EnableScheduling
public class AppConfig
{
  private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  public Docket api()
  {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.marand.auditor"))
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public JmsListenerContainerFactory<?> myFactory(
      final ConnectionFactory connectionFactory,
      final DefaultJmsListenerContainerFactoryConfigurer configurer)
  {
    final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConcurrency("3-15");
    factory.setErrorHandler(throwable -> {

      LOG.error("Error handling message", throwable);

    });
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean
  public MessageConverter jacksonJmsMessageConverter()
  {
    final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }
}
