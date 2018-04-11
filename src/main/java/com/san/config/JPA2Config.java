package com.san.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "h2EntityManagerFactory", transactionManagerRef = "h2TransactionManager", basePackages = { "com.san.h2.repo" })
public class JPA2Config {

	@Bean(name = "h2Datasource")
	public DataSource h2Datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		// dataSource.setUrl("jdbc:h2:file:studentDB;DB_CLOSE_DELAY=-1");//file databse
		dataSource.setUrl("jdbc:h2:mem:db2;DB_CLOSE_DELAY=-1;");// In memory database
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean(name = "h2TransactionManager")
	public JpaTransactionManager h2TransactionManager() {
		return new JpaTransactionManager(h2EntityManagerFactory());
	}

	@Bean(name = "h2EntityManagerFactory")
	public EntityManagerFactory h2EntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(this.h2Datasource());
		emf.setPackagesToScan("com.san.h2.domain");
		emf.setPersistenceUnitName("MyH2PersistenceUnit");
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(va);
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		emf.setJpaProperties(jpaProperties);
		emf.afterPropertiesSet();
		return emf.getObject();
	}
}
