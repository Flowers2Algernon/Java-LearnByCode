package com.jinhong.th58.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory factory;
    static {
        InputStream inputStream = MybatisUtils.class.getClassLoader().getResourceAsStream("Mybatis_config.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    public static SqlSession getSession(){
        return factory.openSession();
    }
}
