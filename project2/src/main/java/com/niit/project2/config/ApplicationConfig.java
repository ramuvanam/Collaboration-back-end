package com.niit.project2.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.project2.model.ForumCategory;
import com.niit.project2.model.Friends;
import com.niit.project2.model.Job;
import com.niit.project2.model.JobApplication;
import com.niit.project2.model.UserBlog;
import com.niit.project2.model.UserForum;
import com.niit.project2.model.UserForumComments;
import com.niit.project2.model.UserProfile;




@Configuration
@EnableTransactionManagement
public class ApplicationConfig {
	// create an instance
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		Class classes[] = new Class[] {UserBlog.class,UserProfile.class,ForumCategory.class,UserForumComments.class,Friends.class,Job.class,JobApplication.class,UserForum.class};
		return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}


	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("COLLABORATION");
		dataSource.setPassword("password");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager hibTransManagement() {
		return new HibernateTransactionManager(sessionFactory());
	}

}
