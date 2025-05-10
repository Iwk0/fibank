package com.fibank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
public class BeanConfiguration {

  @EnableAspectJAutoProxy
  public static class Aspect {}
}
