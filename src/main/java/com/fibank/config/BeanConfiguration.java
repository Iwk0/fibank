package com.fibank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
public class BeanConfiguration {

  @EnableJpaAuditing
  public static class JpaAuditingConfiguration {}

  @EnableAspectJAutoProxy
  public static class Aspect {}
}
