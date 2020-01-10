package com.clbee.pbcms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@EnableTransactionManagement
public class DataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    private final ApplicationContext applicationContext;
    private final DataSource dataSource;

    public DataSourceConfig(ApplicationContext applicationContext, DataSource dataSource) {
        this.applicationContext = applicationContext;
        this.dataSource = dataSource;
    }

//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        DataSource ds = DataSourceBuilder.create().build();
//        return ds;
//    }

    @Bean(name = "SessionFactory")
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("com.clbee.pbcms");
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(applicationContext.getResource("classpath:hibernate.cfg.xml"));

        return sessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws IOException {
        return new DataSourceTransactionManager(dataSource);
    }

}