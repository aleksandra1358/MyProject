package com.project.first;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public TaskService taskService() {
		//return new SimpleTaskService();
		return new DatabaseTaskService();
	}
	
	@Bean
	public TaskDataLoader taskDataLoader() {
		//return new SimpleTaskDataLoader();
		return new DatabaseTaskDataLoader();
	}

	@Bean
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.username("sa")
                .password("")
                .url("jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE")
                .driverClassName("org.h2.Driver")
                .build();
	}
}
