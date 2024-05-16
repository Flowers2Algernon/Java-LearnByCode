package com.jinhong.th58.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "com.jinhong.th58",excludeFilters = @ComponentScan.Filter({Controller.class, EnableWebMvc.class}))
public class SpringConfig {
    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        //关于这部分配置信息，应当写入到配置文件中，但是考虑到spring的配置文件和springboot略有一些差异
        //为了减轻大家的负担，并且后续使用springboot更为多一些，所以我们先不去放置到配置文件中
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/user?characterEncoding=utf-8&useSSL=false");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        return druidDataSource;
    }

    //向容器中去注册当前对象，利用编号取出来的就是SqlSessionFactory；mybatis官方提供的支持
    //使用这种方式的时候，有没有使用mybatis.xml文件？？？没有，需要提供数据库的连接信息
    //所以，我们需要提供数据源信息------》 进一步向容器中去注册一个数据源对象
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }


    //还需要去向容器中去注册一个MapperScannerConfigurer组件
    //这个组件的主要功能是会设定扫描包目录，然后创建对应的mapper实例对象，并且把创建好的mapper实例对象放入到spring容器中
    //那么我们便可以直接使用@Autowired取出来
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        //设定扫描的包目录
        configurer.setBasePackage("com.jinhong.th58.mapper");
        //还需要去关联sqlSessionFactory；因为sqlSessionFactory mybatis的核心组件，其他的功能都需要借助于该对象展开

        //此处直接去写编号即可，为什么可以通过写编号就可以关联？？？？
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }
}
