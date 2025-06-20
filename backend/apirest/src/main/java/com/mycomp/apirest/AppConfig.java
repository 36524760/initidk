package com.mycomp.apirest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:custom.properties")
public class AppConfig {
  @Value("${api.version}")
  private String apiVersion;

  public String getApiVersion() {
    return apiVersion != null? apiVersion : "0.0";
  }
}
