package md.dbalutsel.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import javax.validation.Validator;
import java.util.Properties;

@Configuration
@PropertySource("db.properties")
@EnableTransactionManagement
public class DataConfig {

    @Autowired
    private Environment env;

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(sessionFactory().getObject());
        return jpaTransactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        Resource config = new ClassPathResource("hibernate.cfg.xml");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(config);
        sessionFactory.setPackagesToScan(env.getProperty("bookstore.entity.package"));
        sessionFactory.setDataSource(dataSource());

        Properties customValidator = new Properties();
        customValidator.put("javax.persistence.validation.factory", validator());
        sessionFactory.setHibernateProperties(customValidator);

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("bookstore.db.driver"));
        dataSource.setUrl(env.getProperty("bookstore.db.url"));
        dataSource.setUsername(env.getProperty("bookstore.db.username"));
        dataSource.setPassword(env.getProperty("bookstore.db.password"));
        return dataSource;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}