package com.jinhong.th58;


import com.alibaba.druid.pool.DruidDataSource;
import com.jinhong.th58.controller.AccountController;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;

//注册其他的组件--除了web组件和controller组件之外的其他组件
@Configuration
@ComponentScan(value = "com.jinhong.th58",excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = AccountController.class),@ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
@EnableTransactionManagement
public class SpringConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/jinhong/th58/mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jc.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/user");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.jinhong.th58.mapper");
        scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return scannerConfigurer;
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
