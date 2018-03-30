package com.faceye.feature;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
@SpringBootApplication
//@SpringBootConfiguration
//("classpath*:application.properties")
@EnableJpaRepositories(repositoryBaseClass = com.faceye.feature.repository.jpa.impl.BaseRepositoryImpl.class, basePackages = {
		"com.faceye" }, enableDefaultTransactions = true, repositoryImplementationPostfix = "Impl")
@EnableTransactionManagement
public class Application  {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	
	
	@Bean(name="dataSource", destroyMethod="close")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		builder.setType(EmbeddedDatabaseType.HSQL).build();
		DataSource dataSource = null;
		dataSource = DataSourceBuilder.create().build();
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
//		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
		return jpaVendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPackagesToScan("com.faceye");
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.jdbc.fetch_size", "100");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.query.substitutions", "true 1, false 0");
		properties.setProperty("hibernate.default_batch_fetch_size", "30");
		properties.setProperty("hibernate.max_fetch_depth", "2");
		properties.setProperty("hibernate.generate_statistics", "true");
		properties.setProperty("hibernate.bytecode.use_reflection_optimizer", "true");
		properties.setProperty("hibernate.cache.use_second_level_cache", "true");
		properties.setProperty("hibernate.cache.use_query_cache", "true");
		properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		entityManagerFactoryBean.setJpaProperties(properties);
		entityManagerFactoryBean.setPersistenceUnitName("feature");
		return entityManagerFactoryBean;
	}

//	@Bean
//	public PlatformTransactionManager transactionManager() {
//
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory());
//		return txManager;
//	}
}
