package com.restaurant.api.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	@Value("${db.driver}")
	private String driver;

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;

	@Value("${db.url}")
	private String url;

	@Value("${db.dialect}")
	private String dialect;

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


		driverDataSource.setDriverClassName(driver);
		driverDataSource.setUsername(username);
		driverDataSource.setPassword(password);
		driverDataSource.setUrl(url);
		return driverDataSource;
	}
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",dialect);
		return properties;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager hibernateTransaction() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
	
}
