package com.challenge.customer.configuration;

import static lombok.AccessLevel.PUBLIC;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "challenge")
@FieldDefaults(level = PUBLIC)
public class ApplicationPropertiesConfiguration {

  String basePath;
  Postgresql postgresql;


  @Data
  @FieldDefaults(level = PUBLIC)
  public static class Postgresql {
    String host;
    Integer port;
    String dbname;
    String schema;
    String username;
    String password;
    String sslMode;
  }
}
