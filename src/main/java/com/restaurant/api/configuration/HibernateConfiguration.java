package com.restaurant.api.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	
	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean session= new LocalSessionFactoryBean();
		session.setPackagesToScan("com.restaurant.api.models");
		session.setDataSource(dataSource());
		session.setHibernateProperties(hibernateProperties());
		return session;
	}
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource driverDataSource = new DriverManagerDataSource();
		driverDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverDataSource.setUsername("sql10434852");
		driverDataSource.setPassword("EElMdDDFHS");
		driverDataSource.setUrl("jdbc:mysql://sql10.freesqldatabase.com/sql10434852");
		return driverDataSource;
	}
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		
		return properties;
	}
	
	@Autowired
	@Bean
	public HibernateTransactionManager hibernateTransaction() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
	
}
