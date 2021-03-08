package com.moonlight.roadmapapi.common.beans;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataBeans {
    @Bean
    @ConfigurationProperties(prefix = "spring.data-source.my-sql")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(
            @Value("environment") String envId,
            DataSource dataSource) {
        TransactionFactory trcFactory = new JdbcTransactionFactory();
        Environment env = new Environment(envId, trcFactory, dataSource);
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration(env);
        config.setLazyLoadingEnabled(true);
        return new SqlSessionFactoryBuilder().build(config);
    }
}
