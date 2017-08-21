package com.project.first;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.project.first.task.DatabaseTaskService;
import com.project.first.task.TaskService;
import com.project.first.taskdata.DatabaseTaskDataLoader;
import com.project.first.taskdata.TaskDataLoader;
import com.project.first.taskexecutor.DatabaseTaskExecutor;
import com.project.first.taskexecutor.TaskExecutor;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

	@Bean
	public TaskService taskService() {
		return new DatabaseTaskService();
	}

	@Bean
	public TaskDataLoader taskDataLoader() {
		return new DatabaseTaskDataLoader();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		return new DatabaseTaskExecutor();
	}

	@Bean
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource dataSource() {
		// @formatter:off
		return DataSourceBuilder
				.create()
				.username("sa")
				.password("")
				.url("jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE")
				.driverClassName("org.h2.Driver")
				.build();
		// @formatter:on

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
	}

}
