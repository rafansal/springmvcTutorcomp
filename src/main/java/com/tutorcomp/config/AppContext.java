package com.tutorcomp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class AppContext {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		System.out.println("AppContext :: sessionFactory :: start");

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		try {
			sessionFactory.setDataSource(dataSource());
			sessionFactory.setPackagesToScan(new String[] { "com.tutorcomp.entity" });
			sessionFactory.setHibernateProperties(hibernateProperties());
		} catch (Exception e) {
			System.out.println("AppContext :: sessionFactory :: ERROR :: " + e);
		}
		System.out.println("AppContext :: sessionFactory :: end");

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		System.out.println("AppContext :: dataSource :: start");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try {
			dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
			dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
			dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
			dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		} catch (Exception e) {
			System.out.println("AppContext :: dataSource :: ERROR :: " + e);
		}
		System.out.println("AppContext :: dataSource :: end");

		return dataSource;
	}

	private Properties hibernateProperties() {
		System.out.println("AppContext :: hibernateProperties :: start");

		Properties properties = new Properties();
		try {
			properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
			properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
			properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
			properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		} catch (Exception e) {
			System.out.println("AppContext :: hibernateProperties :: ERROR :: " + e);
		}
		System.out.println("AppContext :: hibernateProperties :: end");

		return properties;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		System.out.println("LoginController :: login :: start");
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		try {
			transactionManager.setSessionFactory(sessionFactory().getObject());
		} catch (Exception e) {
			System.out.println("LoginController :: login :: ERROR :: " + e);
		}
		System.out.println("LoginController :: login :: end");
		return transactionManager;
	}
}