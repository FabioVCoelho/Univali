package app.mobile.ecommerce.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class DataSourceConfig {
     
    @Bean
    public DataSource getDataSource() {      
		    DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName("spring.datasource.driver-class-name");
		    dataSource.setUrl("spring.datasource.url");
		    dataSource.setUsername("spring.datasource.username");
		    dataSource.setPassword("spring.datasource.password");
		    return dataSource;
    }
    
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(getDataSource());
        return liquibase;
    }
}