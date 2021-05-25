package com.multipleds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(
    basePackages = "com.multipleds.repository.container",
    entityManagerFactoryRef = "containEntityManager",
    transactionManagerRef = "containTransactionManager"
)
public class ContainerConfiguration {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean containEntityManager() {
        LocalContainerEntityManagerFactoryBean em
            = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(containDataSource());
        em.setPackagesToScan(
            new String[] { "com.multipleds.entity.container" });

        HibernateJpaVendorAdapter vendorAdapter
            = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("spring.jpa.properties.hibernate.hbm2ddl.auto",
            env.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));
        properties.put("spring.jpa.properties.hibernate.dialect",
            env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }


    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource containDataSource() {

        DriverManagerDataSource dataSource
            = new DriverManagerDataSource();
        dataSource.setDriverClassName(
            env.getProperty("driver-class-name"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("username"));
        dataSource.setPassword(env.getProperty("password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager containTransactionManager() {

        JpaTransactionManager transactionManager
            = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
            containEntityManager().getObject());
        return transactionManager;
    }
}
