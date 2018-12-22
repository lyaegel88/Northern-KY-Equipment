package com.nke.config;


import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@ComponentScan("com.nke")
@Profile("dev")
public class RootApplicationContextConfig {
	
	@Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/nkye?useSSL=false");
        dataSource.setUsername("enter_username");
        dataSource.setPassword("enter_password");
         
        return dataSource;
    }
	
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate() {
	
		return new NamedParameterJdbcTemplate(getDataSource());
	}

}
