package com.farmu.example.common.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class CoreConfig {

    @Primary
    @Bean(name = "exampleDS")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "exampleDB")
    public NamedParameterJdbcTemplate exampleDB(@Qualifier("exampleDS") final DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }

    @Bean
    public SpringLiquibase liquibase() {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/liquibase.xml");
        liquibase.setDataSource(this.dataSource());
        return liquibase;
    }

}
