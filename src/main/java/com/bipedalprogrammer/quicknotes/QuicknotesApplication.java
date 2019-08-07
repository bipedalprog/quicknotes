package com.bipedalprogrammer.quicknotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class QuicknotesApplication {

//    @Bean
//    @ConfigurationProperties("app.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(QuicknotesApplication.class, args);
    }

}
