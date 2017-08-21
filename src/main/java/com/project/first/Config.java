package com.project.first;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.project.first.task.DatabaseTaskService;
import com.project.first.task.TaskService;
import com.project.first.taskdata.DatabaseTaskDataLoader;
import com.project.first.taskdata.TaskDataLoader;
import com.project.first.taskexecutor.DatabaseTaskExecutor;
import com.project.first.taskexecutor.TaskExecutor;

@Configuration
@EnableWebSecurity
public class Config extends WebMvcConfigurerAdapter {

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
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
     // @formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
     // @formatter:on
	}

}
