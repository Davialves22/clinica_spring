package com.br.spring_security.clinica.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataBaseConfiguration {
    // passando as propriedades
    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.driver-class-name}")
    String driver;

    // @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    // pool de conexoes
    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        config.setMaximumPoolSize(10);// maximo de conexões
        config.setMinimumIdle(1);// tamanho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(6000000);// tamanho maximo de uma conexão(10m)
        config.setConnectionTimeout(100000);// tempo para conseguir uma conexão
        config.setConnectionTestQuery("select 1");// query de teste

        return new HikariDataSource(config);
    }
}