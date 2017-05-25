package io.awilson.timein.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"io.awilson.timein.domain"})
@EnableJpaRepositories(basePackages = {"io.awilson.timein.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
