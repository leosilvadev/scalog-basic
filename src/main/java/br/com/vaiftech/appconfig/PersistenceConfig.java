package br.com.vaiftech.appconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import br.com.vaiftech.properties.PersistenceProperties;

@Configuration
@ImportResource(value={"classpath:tx-config.xml"})
@ComponentScan({ "br.com.vaiftech.models" })
public class PersistenceConfig {

	private static Properties hibernateProperties;

	static {
		hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto", PersistenceProperties.getDDLMode());
		hibernateProperties.put("hibernate.dialect", PersistenceProperties.getDialect());
		hibernateProperties.put("hibernate.show_sql", PersistenceProperties.getShowSQL());
		hibernateProperties.put("hibernate.c3p0.min_size", PersistenceProperties.getC3P0MinSize());
		hibernateProperties.put("hibernate.c3p0.timeout", PersistenceProperties.getC3P0Timeout());
		hibernateProperties.put("hibernate.c3p0.max_statements", PersistenceProperties.getC3P0MaxStatements());
		hibernateProperties.put("hibernate.c3p0.idle_test_period", PersistenceProperties.getC3P0IdleTestPeriod());
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
		sessionFactory.setPackagesToScan(new String[] { "br.com.vaiftech.models.entities" });
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public DataSource restDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(PersistenceProperties.getDriverClassName());
		dataSource.setUrl(PersistenceProperties.getURL());
		dataSource.setUsername(PersistenceProperties.getUsername());
		dataSource.setPassword(PersistenceProperties.getPassword());
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}