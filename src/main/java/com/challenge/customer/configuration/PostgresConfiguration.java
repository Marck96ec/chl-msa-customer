package com.challenge.customer.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.util.annotation.NonNull;

@Configuration
@RequiredArgsConstructor
@EnableR2dbcRepositories(basePackages = "com.challenge.customer.repository")
public class PostgresConfiguration extends AbstractR2dbcConfiguration {

    private final ApplicationPropertiesConfiguration crdProperties;

    @Bean
    @Override
    public @NonNull
    PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(crdProperties.postgresql.host)
                .port(crdProperties.postgresql.port)
                .schema(crdProperties.postgresql.schema)
                .database(crdProperties.postgresql.dbname)
                .username(crdProperties.postgresql.username)
                .password(crdProperties.postgresql.password)
                .sslMode(SSLMode.fromValue(crdProperties.postgresql.sslMode))
                .build());
    }
}
