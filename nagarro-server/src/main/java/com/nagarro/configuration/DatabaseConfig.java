package com.nagarro.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		
		ds.setDriverClassName("net.ucanaccess.jdbc.UcanaccessDriver");
		ds.setUrl("jdbc:ucanaccess://" + getClass().getClassLoader().getResource("ms-access-database/accountsdb.accdb").getFile() + ";openExclusive=false;ignoreCase=true");
	
		return ds;
	}
}
