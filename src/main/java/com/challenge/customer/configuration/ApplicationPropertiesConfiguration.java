package com.challenge.customer.configuration;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

@Data
@ConfigurationProperties(prefix = "challenge")
@FieldDefaults(level = PUBLIC)
public class ApplicationPropertiesConfiguration {

  String basePath;
  DataSize maxInMemorySize;
  Integer connectTimeout;
  Integer readTimeout;
  Service services;
  String transactionalProducts;
  RedisConfig redisConfig;
  Postgresql postgresql;
  CoreConfig core;

  @Getter
  @Setter
  @FieldDefaults(level = PRIVATE)
  public static class CoreConfig {

    CoreHeaderConfig header;
  }

  @Getter
  @Setter
  @FieldDefaults(level = PRIVATE)
  public static class CoreHeaderConfig {

    String authUser;
    String authPassword;
    String defaultGeolocation;
    String defaultSession;
    String defaultGuid;
    String defaultIp;
    String agency;
    String device;
    String company;
    String channel;
    String medium;
    String application;
    String creationTrxType;
    String baseInfoTrxType;
    String contactTrxType;
    String signatureTrxId;
    String bank;
    String user;
    String uniqueness;
    String language;
    String customerId;
    String customerIdType;
  }

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

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class RedisConfig {

    Long checkingCustomerTtl;
    String checkingCustomerGroup;
  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class Service {

    SearchAccounts searchAccounts;
    SearchTransactionalContact searchTransactionalContact;
    Customer customer;
  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class Customer {

    String baseUrl;
    String getCifPath;
  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class SearchAccounts {

    String basePath;
    String path;
  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class SearchTransactionalContact {

    String basePath;
    String path;
  }

}
