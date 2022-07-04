package com.aytuncbakir.lms.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"com.aytuncbakir.lms.dao","com.aytuncbakir.lms.service"})
public class SpringRootConfig {

	//TODO: Services, DAO, Datasource, Email Sender or some other business layer beans
	
	@Bean("datasource")
	public BasicDataSource getDataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/library_management_system");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setMaxTotal(2);
		ds.setInitialSize(1);
		ds.setTestOnBorrow(true);
		ds.setValidationQuery("SELECT 1");
		ds.setDefaultAutoCommit(true);
		return ds;
		
	}
}
